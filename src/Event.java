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

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
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
