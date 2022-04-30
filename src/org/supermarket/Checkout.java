/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.supermarket;


import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * @author evenal
 */
public class Checkout {
    // amount of time per product (to scan barcode)
    public static final int PROD_DURATION = 2;
    // amount of time to pay
    public static final int PAY_DURATION = 10;
    //total time for checkout = PAY_DURATION + PROD_DURATION*customer.numProd
    private Deque<Customer> queue;

    SuperMarket shop;
    String name;
    int queueAmount;
    int queueTotalTime;
    int maxQueueSize;

    public Checkout(SuperMarket shop, int i) {
        this.shop = shop;
        this.name = "Checkout " + i;
        this.queue = new LinkedList<>();
        this.maxQueueSize = 0;
    }

    public int calculateCheckoutDuration(int numProducts){
        return (numProducts * PROD_DURATION) + PAY_DURATION;
    }

    public void incrementQueueAmount() {
        queueAmount++;
    }

    public void addTogetherQueueTime(int time) {
        queueTotalTime += time;
    }

    public void setMaxQueueSize(){
        if(queue.size() > maxQueueSize){
            maxQueueSize++;
        }
    }

    public Deque<Customer> getCustomerQueue() {
        return queue;
    }

    public int calculateQueueDelay(Customer customer) {
        Customer customerAheadInQueue = queue.peekLast();
        if(customerAheadInQueue == null || customerAheadInQueue.equals(customer)){
            return 0;
        }
        else
            return customerAheadInQueue.leaveTime - customer.endShoppingTime;
    }

    @Override
    public String toString() {
        return name;
    }

    public String infoPerQueue(){
        int averageWaitTimePerQueue = queueTotalTime / queueAmount;
        return name + ": Customer amount " + queueAmount + ". Total wait time: "
                + queueTotalTime + ". Average wait time: "
                + averageWaitTimePerQueue
                + ". Maximum number of people in the queue at one time: "
                + maxQueueSize + ".";
    }
}
