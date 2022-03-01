package supermarket;

import eventsim.Event;

public class BeginCheckoutEvent extends Event{
    Customer customer;


    public BeginCheckoutEvent(Customer customer) {
        super(customer.beginCheckoutTime);
        this.customer = customer;
    }


    @Override
    public Event happen() {
        return new EndCheckoutEvent(customer);
    }

    @Override
    public String toString() {
        return "Start of checkout for customer: " + customer.name + ", Time: " + getTime() + ". " +
                ". Number of products: " + customer.numProducts + ".";
    }
}
