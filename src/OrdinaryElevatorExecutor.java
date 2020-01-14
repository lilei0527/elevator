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
            for (int i = elevator.getFloor(); i == elevator.getClosePoint(); i = (elevator.isRise() ? i + 1 : i - 1)) {
                move(i, null);
            }
            System.out.println("电梯停靠" + elevator.getFloor() + "楼");
        }

    }


    public synchronized void afterCarry() {
        super.afterCarry();
        elevator.setEvents(null);
    }


}
