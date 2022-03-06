/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventsim;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;


/**
 * The core class of the discrete event simulation
 *
 * @author evenal
 */
public class EventSim {
    /**
     * The one and only instance, i.e. this is a singleton class
     */
    private static final EventSim theSim = new EventSim();

    /* The queue of events - those that happen the earliest first */
    PriorityQueue<Event> eventQueue;

    /**
     * The "current" time
     */
    int clock;
    Random random;


    public static EventSim getInstance() {
        return theSim;
    }


    public static int getClock() {
        return theSim.clock;
    }


    /**
     * Draw a random number in the interval min-max
     *
     */
    public static int nextInt(int min, int max) {
        return min + theSim.random.nextInt(max - min);
    }


    public EventSim() {
        eventQueue = new PriorityQueue<>(new EventTimeComparator());
        random = new Random(42);
    }


    /**
     * Prepare the simulation by adding a list of "start" events
     *
     */
    public void setup(List<Event> initialEvents) {
        eventQueue.addAll(initialEvents);
    }


    public void addEvent(Event event) {
        if (null == event)
            return;
        eventQueue.add(event);
    }


    /**
     * Run the simulation. Advances the time (clock) to the time when the next
     * event happens, executes the next event, and repeats until the event queue
     * is empty. You can also rewrite this to stop at a predetermined time (e.g.
     * closing time)
     */
    public void run() {
        while (!eventQueue.isEmpty()) {
            Event event = eventQueue.poll();
            clock = event.getTime();
            addEvent(event.happen());

            System.out.format("\nTime " + clock +":\t" + event + " \n Event queue:\n");
            for (Event eventQueue : eventQueue)
                System.out.println("     "+ eventQueue.getTime()+":\t" + eventQueue);
        }
    }
}
