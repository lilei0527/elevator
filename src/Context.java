import java.util.ArrayList;
import java.util.List;

/**
 * @author lilei
 **/
public class Context {
    List<Elevator> elevators = new ArrayList<>();
    private Analyzer analyzer;

    private List<Selector> selectors;

    private Selector selector;

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }

    void addSelector(Selector selector) {
        selectors.add(selector);
    }

    void removeElevator(Elevator elevator) {
        elevators.remove(elevator);
    }

    //获取所有直达电梯
//    List<ThroughElevator> getThroughElevators() {
//        List<ThroughElevator> elevators = new ArrayList<>();
//        for (Elevator elevator : this.elevators) {
//            if (elevator instanceof ThroughElevator) {
//                elevators.add((ThroughElevator) elevator);
//            }
//        }
//        return elevators;
//    }

    //获取所有常用电梯
//    List<OrdinaryElevator> getOrdinaryElevators() {
//        List<OrdinaryElevator> elevators = new ArrayList<>();
//        for (Elevator elevator : this.elevators) {
//            if (elevator instanceof ThroughElevator) {
//                elevators.add((ThroughElevator) elevator);
//            }
//        }
//        return elevators;
//    }

    <T extends Elevator> List<T> getElevators(Class<T> c) {
        List<T> elevators = new ArrayList<>();
        for (Elevator elevator : this.elevators) {
            if (elevator.getClass().equals(c)) {
                elevators.add((T) elevator);
            }
        }
        return elevators;
    }

    <T extends Selector> T getSelector(Class<T> c) {
        for (Selector selector : this.selectors) {
            if (selector.getClass().equals(c)) {
                return (T) selector;
            }
        }
        return null;
    }


    void start() {
        System.out.println("电梯初始化完毕:");
        detail();
        for (Elevator elevator : elevators) {
            Thread thread = new Thread(elevator);
            thread.start();
        }
    }

    void detail() {

        for (Elevator elevator : elevators) {
            System.out.println(elevator.toString());
        }
    }

    void press(int floor, String direction, String number, int destination) {
        Event event = new Event(floor, direction, number, destination);
        analyzer.analyzer(event);
    }

    public static void main(String[] args) {
        Context context = new Context();
        Elevator elevator = new ThroughElevator("A1", StateEnum.FREE.getType(), 0, 999, 0);
        Elevator elevator1 = new ThroughElevator("A2", StateEnum.FREE.getType(), 0, 999, 0);
        Elevator elevator2 = new ThroughElevator("A3", StateEnum.FREE.getType(), 0, 999, 0);
        context.addElevator(elevator);
        context.addElevator(elevator1);
        context.addElevator(elevator2);

        new ThroughElevatorExecutor(elevator);
        new ThroughElevatorExecutor(elevator1);
        new ThroughElevatorExecutor(elevator2);

        new Analyzer(context);
        new ThroughElevatorSelector(context);

        context.start();

        context.press(4, "up", "A1", 9);

        context.press(7, "down", "A2", 0);

        context.press(2, "up", "A1", 10);

        context.press(6, "up", "A1", 10);

        context.press(9, "up", "A1", 2);


    }
}
