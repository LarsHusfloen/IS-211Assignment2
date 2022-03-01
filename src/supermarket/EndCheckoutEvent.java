package supermarket;

import eventsim.Event;
import eventsim.EventSim;

public class EndCheckoutEvent extends Event{
    Customer customer;


    public EndCheckoutEvent(Customer customer) {
        super(EventSim.getClock() + customer.checkoutDuration);
        this.customer = customer;
    }


    @Override
    public Event happen() {
        customer.leaveTime = customer.checkoutTime + customer.checkoutDuration;
        return null;
    }

    @Override
    public String toString() {
        return "End of checkout for customer: " + customer.name + ". Checked out at time: " + customer.endCheckoutTime +
                ". Number of products: " + customer.numProducts + ".";
    }
}
