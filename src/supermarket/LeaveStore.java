package supermarket;

import eventsim.Event;

public class LeaveStore extends Event {
    Customer customer;
    Checkout checkout;

    public LeaveStore(Customer customer) {
        super(customer.leaveTime);
        this.customer = customer;
        checkout = customer.getCheckout();
    }

    @Override
    public Event happen() {
        if(customer.numProducts != 0){
            checkout.getCustomerQueue().remove(customer);
        }
        return null;
    }

    @Override
    public String toString() {
        if(customer.numProducts == 0){
            return customer +" leaves the store without buying anything.";
        } else {
            return customer +" leaves the store.";
        }
    }
}
