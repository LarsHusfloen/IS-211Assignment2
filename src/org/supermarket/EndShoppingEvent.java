/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.supermarket;

import org.eventsim.Event;
import org.eventsim.EventSim;


/**
 * A customer finishes shopping and heads for the checkout with the shortest
 * queue
 *
 * @author evenal
 */
public class EndShoppingEvent extends Event {
    Customer customer;


    public EndShoppingEvent(Customer customer) {
        super(EventSim.getClock() + customer.shoppingDuration);
        this.customer = customer;
    }

    @Override
    public Event happen() {
        if(customer.numProducts == 0){
            customer.leaveTime = customer.endShoppingTime + 1;
            return new LeaveStore(customer);
        } else {
            customer.chooseCheckout();
            return new BeginCheckoutEvent(customer);
        }
    }

    @Override
    public String toString() {
        return customer + " finished shopping. Number of products: "
                + customer.numProducts + ".";
    }
}