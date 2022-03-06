package supermarket;

import eventsim.Event;
import eventsim.EventSim;

public class EndCheckoutEvent extends Event{
    Customer customer;
    Checkout checkout;

    public EndCheckoutEvent(Customer customer, Checkout checkout) {
        super(customer.checkoutTime);
        this.customer = customer;
        this.checkout = checkout;
    }

    @Override
    public Event happen() {
        customer.leaveTime = customer.checkoutTime + customer.checkoutDuration;
        return new LeaveStore(customer);
    }

    @Override
    public String toString() {
        return customer + " was checked out at "+ checkout +". Checkout duration: "
                + customer.checkoutDuration + ".";
    }
}
