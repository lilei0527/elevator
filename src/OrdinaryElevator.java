import java.util.ArrayList;
import java.util.List;

/**
 * @author lilei
 **/
public class OrdinaryElevator extends Elevator {
    //接收到的事件
    private volatile List<Event> events = new ArrayList<>();

    @Override
    public void run() {

    }

    public OrdinaryElevator(String number, String state, int floor, float nuclearLoading, float weight) {
        super(number, state, floor, nuclearLoading,weight);
    }

    @Override
    public void setEvent(Event event) {
    }

    @Override
    public Event getEvent() {
        return null;
    }

    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
