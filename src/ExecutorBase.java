
/**
 * @author lilei
 **/
public abstract class ExecutorBase<T extends Elevator> implements Executor {
    T elevator;

    ExecutorBase(T elevator) {
        this.elevator = elevator;
        elevator.setExecutor(this);
    }

    //电梯开始载客之前
    void beforeCarry(int eventFloor) {
        if (elevator.getFloor() < eventFloor) {
            elevator.setState(StateEnum.RISE.getType());
        } else if (elevator.getFloor() > eventFloor) {
            elevator.setState(StateEnum.DROP.getType());
        } else {
            elevator.setState(StateEnum.PAUSE.getType());
        }
    }



    void beforeMoveToPoint(int destinationFloor){
        if(!elevator.getState().equals(StateEnum.OVERWEIGHT.getType())){
            if(elevator.getFloor()<destinationFloor){
                elevator.setState(StateEnum.RISE.getType());
            }else if(elevator.getFloor()>destinationFloor){
                elevator.setState(StateEnum.DROP.getType());
            }else {
                elevator.setState(StateEnum.FREE.getType());
            }
        }
    }

    //电梯移动到指定楼层 point:指定楼层，state:到达后电梯的状态
     void move(int point, String state) throws InterruptedException {
        if (elevator.getState().equals(StateEnum.RISE.getType())) {
            System.out.println(elevator.getNumber() + "电梯上升......");
            while (point > elevator.getFloor()) {
                Thread.sleep(2 * 1000); //3S
                elevator.setFloor(elevator.getFloor() + 1);
                System.out.println(elevator.getNumber() + "电梯当前位置" + elevator.getFloor() + "楼");
            }
            if (state != null) {
                elevator.setState(state);
            }
        } else if (elevator.getState().equals(StateEnum.DROP.getType())) {
            System.out.println(elevator.getNumber() + "电梯下降......");
            while (point < elevator.getFloor()) {
                Thread.sleep( 1000); //1S
                elevator.setFloor(elevator.getFloor() - 1);
                System.out.println(elevator.getNumber() + "电梯当前位置" + elevator.getFloor() + "楼");
            }
            if (state != null) {
                elevator.setState(state);
            }
        }
    }

    //电梯结束载客  一定要同步，不然setState和setEvent分开会出现问题
    public synchronized void afterCarry() {
        elevator.setState(StateEnum.FREE.getType());
        elevator.setWeight(0);
    }
}
