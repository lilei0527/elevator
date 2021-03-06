import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author lilei
 **/
public class OrdinaryElevator extends Elevator<OrdinaryElevatorExecutor> {
    //接收到的事件
    private volatile List<Event> events ;

    @Override
    public void run() {
        while (!getState().equals(StateEnum.FAULT.getType())) {
            if (getEvents() != null) {
                //接受到指令
                System.out.println("电梯" + this.getNumber() + "接受到指令");
                super.run();
            }
        }
    }


    OrdinaryElevator(String number, String state, int floor, float nuclearLoading, float weight) {
        super(number, state, floor, nuclearLoading, weight);
    }

    //可以一同上升或下降的电梯
    boolean canMerge(Event event) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("是否能merge:"+"elevator:"+this.toString()+"event:"+event.toString());
        return (getState().equals(StateEnum.RISE.getType()) && event.getDirection().equals("up")) && (getFloor() <= event.getFloor())
                || (getState().equals(StateEnum.DROP.getType()) && event.getDirection().equals("down")) && (getFloor() >= event.getFloor());
    }

    //判断电梯是否完成所有的指令
    boolean finishAll() {
        List<Integer> points = getPoints();
        return getState().equals(StateEnum.RISE.getType()) && points.get(points.size() - 1) == getFloor() ||
                getState().equals(StateEnum.DROP.getType()) && points.get(0) == getFloor();
    }

    private List<Integer> getPoints() {
        Set<Integer> set = new TreeSet<>();
        List<Event> events = getEvents();
        for (Event event : events) {
            set.add(event.getFloor());
            set.add(event.getDestination());
        }
        return new ArrayList<>(set);
    }

    //取得电梯行进方向最近的一个停靠点
    int getClosePoint() {
        List<Integer> points = getPoints();
        System.out.println("电梯所有停靠点:"+points.toString());
        if (isRise()) {
            for (Integer point : points) {
                if (point > getFloor()) {
                    return point;
                }
            }
        }

        if (isDrop()) {
            for(int i=points.size()-1;i>=0;i--){
                if(points.get(i)<getFloor()){
                    return points.get(i);
                }
            }
        }

        return 0;
    }

    void addEvent(Event event) {
        events.add(event);
    }

    List<Event> getEvents() {
        return events;
    }


    void setEvents(@SuppressWarnings("SameParameterValue") List<Event> events) {
        this.events = events;
    }

    boolean isContrary() {
        return (getState().equals(StateEnum.RISE.getType()) && events.get(0).getDirection().equals("down"))
                || (getState().equals(StateEnum.DROP.getType()) && events.get(0).getDirection().equals("up"));
    }

    String getContraryState() {
        return getState().equals(StateEnum.RISE.getType()) ? StateEnum.DROP.getType() : StateEnum.RISE.getType();
    }
}
