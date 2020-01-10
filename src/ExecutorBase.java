
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

    //电梯结束载客  一定要同步，不然setState和setEvent分开会出现问题
    public synchronized void afterCarry() {
        elevator.setState(StateEnum.FREE.getType());
        elevator.setWeight(0);
    }
}
