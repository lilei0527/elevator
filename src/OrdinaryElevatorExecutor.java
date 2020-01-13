
/**
 * @author lilei
 **/
public class OrdinaryElevatorExecutor extends ExecutorBase<OrdinaryElevator> {
    public OrdinaryElevatorExecutor(OrdinaryElevator elevator) {
        super(elevator);
    }

    @Override
    public void execute() throws InterruptedException {
        beforeCarry(elevator.getEvents().get(0).getFloor());

    }


    public void moveToNext() throws InterruptedException {
        while (!elevator.finishAll()){
            for(int i=elevator.getFloor();i==elevator.getClosePoint();i=(elevator.isRise()?i+1:i-1)){
                move(i,null);
            }
        }
    }



    public synchronized void afterCarry() {
        super.afterCarry();
        elevator.setEvents(null);
    }


}
