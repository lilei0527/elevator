
/**
 * @author lilei
 * 直达电梯
 **/
public class ThroughElevator extends Elevator {
    //接收到的事件
    private volatile Event event;

     ThroughElevator(String number, String state, int floor, float nuclearLoading, float weight) {
        super(number, state, floor, nuclearLoading, weight);
    }


    @Override
    public void run() {
        while (!getState().equals(StateEnum.FAULT.getType())) {
            if (getEvent() != null) {
                //接受到指令
                System.out.println("电梯" + this.getNumber() + "接受到指令");
                super.run();
            }
        }
    }

    public Event getEvent() {
        return event;
    }



    public void setEvent(Event event) {
        this.event = event;
    }


}