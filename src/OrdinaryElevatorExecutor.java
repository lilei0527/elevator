import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    }

    public synchronized void afterCarry() {
        super.afterCarry();
        elevator.setEvents(null);
    }

    public Set<Integer> getPoints() {
        Set<Integer> set = new HashSet<>();
        if (elevator != null) {
            List<Event> events =elevator.getEvents();
            for (Event event : events) {
                set.add(event.getFloor());
                set.add(event.getDestination());
            }
        }
        return set;
    }
}
