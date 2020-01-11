
/**
 * @author lilei
 * 直达电梯选择器
 **/
public class ThroughElevatorSelector extends SelectorBase<ThroughElevator> {


    ThroughElevatorSelector(Context context) {
        elevators = context.getAnalyzer().getElevators(ThroughElevator.class);
        context.addSelector(this);
    }

    @Override
    public Elevator choice(Event event) {
        return choiceClose(event);
    }

    @Override
    ThroughElevator changeState(ThroughElevator elevator, Event event) {
        elevator.setEvent(event);
        elevator.setState(StateEnum.CHECKED.getType());
        return elevator;
    }
}
