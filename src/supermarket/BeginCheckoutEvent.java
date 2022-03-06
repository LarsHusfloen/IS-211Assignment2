package supermarket;

import eventsim.Event;

public class BeginCheckoutEvent extends Event{
    Customer customer;
    Checkout checkout;

    public BeginCheckoutEvent(Customer customer) {
        super(customer.endShoppingTime);
        this.customer = customer;
    }


    @Override
    public Event happen() {
        checkout = customer.getCheckout();
        customer.checkoutDuration = checkout.calculateCheckoutDuration(customer.numProducts);
        customer.queueWaitDuration = checkout.calculateQueueDelay(customer);
        customer.checkoutTime = customer.endShoppingTime+1+customer.queueWaitDuration;
        customer.leaveTime = customer.checkoutTime + customer.checkoutDuration;
        checkout.getCustomerQueue().add(customer);
        checkout.incrementQueueAmount();
        checkout.addTogetherQueueTime(customer.queueWaitDuration);
        checkout.setMaxQueueSize();

        return new EndCheckoutEvent(customer, checkout);
    }

    @Override
    public String toString() {
        return (checkout==null ? customer + " enter queue" : customer + " enter queue " + checkout)
                + ". Queue wait time: " + customer.queueWaitDuration + ".";
    }
}
