import java.util.List;

/**
 * @author lilei
 **/
abstract class SelectorBase<T extends Elevator> implements Selector {
    List<T> elevators;

    //最近的空闲电梯
    T choiceClose(Event event) {
        int close = 0;
        for (T elevator : elevators) {
            if (elevator.getState().equals(StateEnum.FREE.getType())) {
                close = Math.min(Math.abs(event.getFloor() - elevator.getFloor()), close);
                if (close == 0) {
                    return changeState(elevator, event);
                }
            }
        }

        if (close == 0) {
            return null;
        }

        T temp = null;
        for (T elevator : elevators) {
            if (elevator.getState().equals(StateEnum.FREE.getType())) {
                if (Math.abs(event.getFloor() - elevator.getFloor()) == close) {
                    if (event.getFloor() - elevator.getFloor() < 0) {
                        return changeState(elevator, event);
                    } else {
                        temp = elevator;
                    }
                }
            }
        }
        if (temp != null) {
            return changeState(temp, event);
        }
        return null;
    }

    //电梯被中后状态的改变
    abstract T changeState(T elevator, Event event);
}