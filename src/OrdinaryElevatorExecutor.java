import java.util.ArrayList;
import java.util.List;

/**
 * @author lilei
 **/
public class OrdinaryElevatorExecutor extends ExecutorBase<OrdinaryElevator> {
    public OrdinaryElevatorExecutor(OrdinaryElevator elevator) {
        super(elevator);
    }

    @Override
    public void execute() throws InterruptedException {
        beforeCarry(elevator.getEvents().get(0).getFloor());
        elevator.printState();
        moveToNext();
        if (elevator.isContrary()) {
            List<Event> events = new ArrayList<>();
            events.add(elevator.getEvents().get(0));
            elevator.setEvents(events);
            elevator.setState(elevator.getContraryState());
            moveToNext();
        }
        afterCarry();
    }


    private void moveToNext() throws InterruptedException {
        while (!elevator.finishAll()) {
            int point = elevator.getClosePoint();
            System.out.println("最近停靠点："+point);
            for (int i = elevator.getFloor(); point== elevator.getClosePoint(); i = (elevator.isRise() ? i + 1 : i - 1)) {
                move(i, null);
            }
            stop();
        }
    }

    private void stop() throws InterruptedException {
        System.out.println("电梯停靠" + elevator.getFloor() + "楼");
        Thread.sleep(3000); //3s
    }

    public synchronized void afterCarry() {
        super.afterCarry();
        elevator.setEvents(null);
    }
}
