import java.util.List;

/**
 * @author lilei
 **/
public abstract class Elevator implements Runnable {
    //电梯编号
    private final String number;
    //电梯状态
    private volatile String state;
    //电梯当前楼数
    private volatile int floor;
    //电梯核载
    private final float nuclearLoading;
    //电梯当前承载重量
    private volatile float weight;


    public Executor executor;

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    //模拟电梯接受到指令，开始运行
    @Override
    public abstract void run();


    public synchronized void addWeight(float weight) {
        this.weight += weight;
    }

    public boolean isOverWeight() {
        return weight > nuclearLoading;
    }

    public Elevator(String number, String state, int floor, float nuclearLoading, float weight) {
        this.number = number;
        this.state = state;
        this.floor = floor;
        this.nuclearLoading = nuclearLoading;
        this.weight = weight;
    }

    public String getNumber() {
        return number;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public float getNuclearLoading() {
        return nuclearLoading;
    }


    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "Elevator{" +
                "number='" + number + '\'' +
                ", state='" + state + '\'' +
                ", floor=" + floor +
                ", nuclearLoading=" + nuclearLoading +
                ", weight=" + weight +
                ", executor=" + executor +
                '}';
    }

    boolean isSameDirection(Event event) {
        return (getState().equals(StateEnum.RISE.getType()) && event.getDirection().equals("up")) ||
                (getState().equals(StateEnum.DROP.getType()) && event.getDirection().equals("down"));
    }

    //可以一同上升或下降的电梯
    boolean canMerge(Event event) {
        return (getState().equals(StateEnum.RISE.getType()) && event.getDirection().equals("up")) && (getFloor() <= event.getFloor())
                || (getState().equals(StateEnum.DROP.getType()) && event.getDirection().equals("down")) && (getFloor() >= event.getFloor());
    }
}