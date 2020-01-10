/**
 * @author lilei
 **/
public class Event {
    private int floor;

    private String direction;

    private String number;

    private int destination;

    public Event(int floor, String direction, String number, int destination) {
        this.floor = floor;
        this.direction = direction;
        this.number = number;
        this.destination = destination;
    }

    int getFloor() {
        return floor;
    }


    String getDirection() {
        return direction;
    }


    String getNumber() {
        return number;
    }


    int getDestination() {
        return destination;
    }


    @Override
    public String toString() {
        return "Event{" +
                "floor=" + floor +
                ", direction='" + direction + '\'' +
                ", number='" + number + '\'' +
                ", destination=" + destination +
                '}';
    }
}
