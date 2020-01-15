
/**
 * @author lilei
 **/
@SuppressWarnings("unused")
public abstract class Elevator<T extends Executor> implements Runnable {
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


    private T executor;

    T getExecutor() {
        return executor;
    }

    void setExecutor(T executor) {
        this.executor = executor;
    }

    //模拟电梯接受到指令，开始运行
    @Override
    public void run(){
        try {
            if (executor != null) {
                executor.execute();
            }
        } catch (InterruptedException e) {
            System.out.println("异常");
            e.printStackTrace();
        }
    }


    synchronized void addWeight(float weight) {
        this.weight += weight;
    }

    boolean isOverWeight() {
        return weight > nuclearLoading;
    }

    Elevator(String number, String state, int floor, float nuclearLoading, float weight) {
        this.number = number;
        this.state = state;
        this.floor = floor;
        this.nuclearLoading = nuclearLoading;
        this.weight = weight;
    }

    String getNumber() {
        return number;
    }


    String getState() {
        return state;
    }

    void setState(String state) {
        this.state = state;
    }

    int getFloor() {
        return floor;
    }

    void setFloor(int floor) {
        this.floor = floor;
    }


    void setWeight(@SuppressWarnings("SameParameterValue") float weight) {
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

    boolean isRise(){
        return state.equals(StateEnum.RISE.getType());
    }

    boolean isDrop(){
        return state.equals(StateEnum.DROP.getType());
    }

//    boolean isSameDirection(Event event) {
//        return (getState().equals(StateEnum.RISE.getType()) && event.getDirection().equals("up")) ||
//                (getState().equals(StateEnum.DROP.getType()) && event.getDirection().equals("down"));
//    }



}