/**
 * @author lilei
 **/
public class ThroughElevatorExecutor extends ExecutorBase<ThroughElevator> {
     ThroughElevatorExecutor(ThroughElevator elevator) {
        super(elevator);
    }

    @Override
    public void execute() throws InterruptedException {

        beforeCarry(elevator.getEvent().getFloor());

        moveToEvent();

        carry(12);

        moveToPoint();

        afterCarry();
    }


    //电梯开始载客
    private void carry(@SuppressWarnings("SameParameterValue") float weight) {
        System.out.println("电梯开始载客");
        elevator.addWeight(weight);
        if (elevator.isOverWeight()) {
            elevator.setState(StateEnum.OVERWEIGHT.getType());
        }else {
            beforeMoveToPoint(elevator.getEvent().getFloor());
        }
    }

    //电梯结束载客  一定要同步，不然setState和setEvent分开会出现问题
    public synchronized void afterCarry() {
        super.afterCarry();
        elevator.setEvent(null);
    }

    //模拟直达电梯准备到达目的地
    private void moveToPoint() throws InterruptedException {
        System.out.println(elevator.getNumber() + "电梯前往目的地楼层:" + elevator.getEvent().getDestination() + "楼");
        move(elevator.getEvent().getDestination(), null);
        System.out.println(elevator.getNumber() + "电梯到达目的地");
    }


    //模拟直达电梯准备到达按电梯楼层
    private void moveToEvent() throws InterruptedException {
        System.out.println(elevator.getNumber() + "电梯前往按电梯楼层:" + elevator.getEvent().getFloor() + "楼");
        move(elevator.getEvent().getFloor(), StateEnum.PAUSE.getType());
        System.out.println(elevator.getNumber() + "电梯到达按电梯楼层");
    }

    //电梯移动到指定楼层 point:指定楼层，state:到达后电梯的状态
    private void move(int point, String state) throws InterruptedException {
        if (elevator.getState().equals(StateEnum.RISE.getType())) {
            System.out.println(elevator.getNumber() + "电梯上升......");
            while (point > elevator.getFloor()) {
                Thread.sleep(2 * 1000); //2S
                elevator.setFloor(elevator.getFloor() + 1);
                System.out.println(elevator.getNumber() + "电梯当前位置" + elevator.getFloor() + "楼");
            }
            if (state != null) {
                elevator.setState(state);
            }
        } else if (elevator.getState().equals(StateEnum.DROP.getType())) {
            System.out.println(elevator.getNumber() + "电梯下降......");
            while (point < elevator.getFloor()) {
                Thread.sleep(2 * 1000); //2S
                elevator.setFloor(elevator.getFloor() - 1);
                System.out.println(elevator.getNumber() + "电梯当前位置" + elevator.getFloor() + "楼");
            }
            if (state != null) {
                elevator.setState(state);
            }
        }
    }
}
