package supermarket;

import eventsim.Event;
import eventsim.EventSim;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author evenal
 */
public class SuperMarket {

    public static final int NUM_CHECKOUTS = 2;
    public static final int NUM_CUSTOMERS = 8;

    Checkout[] checkouts;
    List<Customer> customers;
    List<Event> init;


    public SuperMarket() {
        checkouts = new Checkout[NUM_CHECKOUTS];
        for (int i = 0; i < NUM_CHECKOUTS; i++)
            checkouts[i] = new Checkout(this, i);
        customers = new ArrayList<>();
        init = new ArrayList<>();
        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            Customer customer = new Customer(this, i);
            init.add(new BeginShoppingEvent(customer));
            customers.add(customer);
        }
    }


    public Checkout getCheckoutByShortestQueue(){
        Checkout shortestCheckoutQueue = null;
        for (Checkout checkout:checkouts){
            if(shortestCheckoutQueue==null){
                shortestCheckoutQueue = checkout;
            } else {
                if(shortestCheckoutQueue.getCustomerQueue().size()>checkout.getCustomerQueue().size())
                    shortestCheckoutQueue = checkout;
            }
        }
        return shortestCheckoutQueue;
    }


    public void startSim() {
        EventSim sim = EventSim.getInstance();
        sim.setup(init);
        sim.run();
    }
}
