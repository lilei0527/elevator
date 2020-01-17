import java.util.ArrayList;
import java.util.List;


/**
 * @author lilei
 * 分析使用哪一个选择器
 **/
class Analyzer {
    private Context context;

    Analyzer(Context context) {
        this.context = context;
        context.setAnalyzer(this);
    }


    @SuppressWarnings("unchecked")
    <T extends Elevator> List<T> getElevators(Class<T> c) {
        List<T> elevators = new ArrayList<>();
        for (Elevator elevator : context.getElevators()) {
            if (elevator.getClass().equals(c)) {
                elevators.add((T) elevator);
            }
        }
        return elevators;
    }

    @SuppressWarnings("unchecked")
    private <T extends Selector> T getSelector(Class<T> c) {
        for (Selector selector : context.getSelectors()) {
            if (selector.getClass().equals(c)) {
                return (T) selector;
            }
        }
        return null;
    }

    //是否是直达电梯
    private boolean isThroughElevator(String number) {
        List<ThroughElevator> throughElevators = getElevators(ThroughElevator.class);
        for (ThroughElevator throughElevator : throughElevators) {
            if (throughElevator.getNumber().equals(number)) {
                return true;
            }
        }
        return false;
    }

    //是否是常用电梯
    private boolean isOrdinaryElevator(String number) {
        List<OrdinaryElevator> ordinaryElevators = getElevators(OrdinaryElevator.class);
        for (OrdinaryElevator ordinaryElevator : ordinaryElevators) {
            if (ordinaryElevator.getNumber().equals(number)) {
                return true;
            }
        }
        return false;
    }

    void analyzer(Event event) {
        Thread thread = new Thread(() -> {
            if (isThroughElevator(event.getNumber())) {
                doSelect(ThroughElevatorSelector.class, event);
            }
            if (isOrdinaryElevator(event.getNumber())) {
                doSelect(OrdinaryElevatorSelector.class, event);
            }
        });
        thread.start();
    }

    private   <T extends Selector> void doSelect(Class<T> clazz, Event event) {
        Elevator selectedElevator = null;
        while (selectedElevator == null) {
            Selector selector = getSelector(clazz);
            if (selector != null) {
                selectedElevator = selector.choice(event);
            }
        }
        System.out.println(event.toString() + "选择的电梯:" + selectedElevator.toString());
    }
}
