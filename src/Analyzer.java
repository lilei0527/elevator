import java.util.List;


/**
 * @author lilei
 * 分析使用哪一个选择器
 **/
public class Analyzer {
    Context context;

    public Analyzer(Context context) {
        this.context = context;
        context.setAnalyzer(this);
    }


    //是否是直达电梯
    boolean isThroughElevator(String number) {
        List<ThroughElevator> throughElevators = context.getElevators(ThroughElevator.class);
        for (ThroughElevator throughElevator : throughElevators) {
            if (throughElevator.getNumber().equals(number)) {
                return true;
            }
        }
        return false;
    }

    //是否是常用电梯
    boolean isOrdinaryElevator(String number) {
        List<OrdinaryElevator> ordinaryElevators = context.getElevators(OrdinaryElevator.class);
        for (OrdinaryElevator ordinaryElevator : ordinaryElevators) {
            if (ordinaryElevator.getNumber().equals(number)) {
                return true;
            }
        }
        return false;
    }

    void analyzer(Event event) {
        if (isThroughElevator(event.getNumber())) {
            doSelect(ThroughElevatorSelector.class, event);
        }

        if (isOrdinaryElevator(event.getNumber())) {
            doSelect(OrdinaryElevatorSelector.class, event);
        }
    }

    <T extends Selector> void doSelect(Class<T> clazz, Event event) {
        Elevator selectedElevator = null;
        while (selectedElevator == null) {
            Selector selector = context.getSelector(clazz);
            selectedElevator = selector.choice(event);
        }
        System.out.println(event.toString() + "选择的电梯:" + selectedElevator.toString());
    }
}
