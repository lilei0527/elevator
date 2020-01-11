
/**
 * @author lilei
 **/
public class OrdinaryElevatorSelector extends SelectorBase<OrdinaryElevator> {

    OrdinaryElevatorSelector(Context context) {
        elevators = context.getAnalyzer().getElevators(OrdinaryElevator.class);
        context.addSelector(this);
    }

    @Override
    public OrdinaryElevator choice(Event event) {
        OrdinaryElevator elevator = choiceClose(event);
        if(elevator!=null){
            return elevator;
        }
        return choiceMerge(event);
    }

    //最近的可以一同上升或下降的电梯
    private OrdinaryElevator choiceMerge(Event event){
        int close = 0;
        for (OrdinaryElevator elevator : elevators) {
            if (elevator.canMerge(event)) {
                close = Math.min(Math.abs(event.getFloor() - elevator.getFloor()), close);
                if (close == 0) {
                   return changeState(elevator,event);
                }
            }
        }

        if (close == 0) {
            return null;
        }

        OrdinaryElevator temp = null;
        for (OrdinaryElevator elevator : elevators) {
            if (elevator.canMerge(event)) {
                if (Math.abs(event.getFloor() - elevator.getFloor()) == close) {
                    if (event.getFloor() - elevator.getFloor() < 0) {
                        return changeState(elevator,event);
                    } else {
                        temp = elevator;
                    }
                }
            }
        }
        if (temp != null) {
            return changeState(temp,event);
        }
        return null;
    }




    @Override
    OrdinaryElevator changeState(OrdinaryElevator elevator, Event event) {
        elevator.addEvent(event);
        elevator.setState(StateEnum.CHECKED.getType());
        return elevator;
    }
}
