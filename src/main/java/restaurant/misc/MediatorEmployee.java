package restaurant.misc;

import restaurant.model.Order;

public class MediatorEmployee extends MediatorPerson {

    public MediatorEmployee(Mediator mediator, Order.Status status) {
        super(mediator);
        this.status = status;
    }

    @Override
    public Order.Status updateStatus() {
        return status;
    }
}
