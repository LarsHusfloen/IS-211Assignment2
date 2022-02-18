# TASK : Discrete event simulation

Discrete event simulation models a domain as a sequence of events that instantaneously changes the state of the domain. This differs from continuous simulation, which is used in ship and flight simulators, and a large number of games.

The difference is mainly due to the handling of time. Continous simulations try to run synchroneously with real time, while discrete event simulation skips over the time between events. This makes discrete event simulation less resource demanding than continuous simulation. Consequently discrete event simulations will run faster (or larger simulations can be run on the same equipment).

Let's look at an example. If we have an event model of the customers in a supermarket, the first interesting event would be when the customer enters the supermarket. The customer will leave the supermarket through the checkouts som time later. How long he will stay in the supermarket, and how many products he will buy, will be computed by the simulation. If he, for example, spends 25 minutes in the supermarket buying 10 products, he will join the queue at the checkout 25 minutes after he entered.

That will be the next event if this was the only customer, but he is not: A second customer enters 5 minutes after the first. This customer spends only 10 minutes in the supermarket buying 4 products. So he will join the checkout queue 10 minutes before the first customer.

Both customers will be checked out by the till lady, after the customers in front of them in the queue has paid and left. Depending on how precise we want to be, the checkout process coult be modelled as taking 10 minutes regardless of what the customer has bought, or it could take, say 20 seconds to register each product, and 3 minutes to pay. Then the second customer would spend 4min.20sec at the till when it is his turn, while the second customer will spend 6min.20sec. at the till.

Why would we run a simulation in this way? Discrete event simulations are often used in planning, because they give answers to questions like: How many customers are in the supermarket at any time of day? How long are the checkout queues? Running the simulation with different parameters, e.g. a larger number of checkouts, will hopefully provide the information that is needed to decide what to do.

## Assignment

You shall write a discrete event simulation of a supermarket. The simulation will be used to determine how long the checkout queues will be with different numbers of checkout points. Your program should monitor the queue at each checkout point, to find the average and maximum size of the queues. You may also monitor time spent queueing, in additions to, or instead of the queue lengts.

These are the events that can happen:

1.  A customer enters the supermarket. This event will happen ramdomly. We assume that the customer will be picking products from the shelves until he wants to check out.
2.  A random amount of time after a customer entered, he will stop picking products, and start looking for a checkout point. By now his cart will contain a random number of products.
3.  The customer selects a checkout point, and joins the queue. Then he just has to wait until it is his turn to check out.
4.  A checkout point starts serving the first customer in the queue (i.e. the customer object will be removed from the queue). The time that will be spent on checking out the customer is made up of a constant times the number of products in the cart plus a constant time to pay
5.  A customer has been checked out and leaves the supermarket.

Most of these events do not just happen: If and when they happen are a result of earlier events. Some of these dependencies are obvious but not all

*   When a customer enters, he will have to leave again. Either he leaves without buying anything (Note: requires a new event type in addition to those above), or he will look for a checkout point. So when an event of type 1 happens, an event of type 2 will happen some time later.
*   When an event of type 2 happens, a type 3 event will happen a short time later.
*   A type 3 event do not cause any new events, except if the checkout point is not serving any customer, and the queue is empty. In that case a type 4 event will happen a short time later. (If the checkout wasn't doing anything it wakes up when the customer joins the queue.
*   When a type 4 event happens, a type 5 event will happen a some time later, how much depends on the number of products in the customers cart.(When the checkout process starts, it will end some time)
*   When a type 5 event happens, a type 4 event will happen a short time later (when a customer has been checked out, start checking out the next one)

The phrase "...a short time later" means that an event should happen later than the one that caused it, but how much later can/should be the smallest amount of time that is possible.

### Comments and hints

*   You are free to decide the unit of time. The easiest is to use integers, and start the simulation at time 0 (zero). You must also assign values to the CONSTANTs defined in the code.
*   Some values can be calculated before they would be known in reality. For example you can calculate the time from entry to joining a queue, the number of products in the cart, and the time it takes to check out those products, before the customer has even entered the supermarket.
*   Handling several checkouts can be confusing. You should start with a single checkout point, and be sure that your code works then, before you move on to choosing between checkouts, and getting all customers through the checkouts in the correct order.
*   The customer has to select a checkout point (if there is more than one). You can either pick a random checkout, or try to be smart and select the checkout with the shortest queue.
*   Be aware that there is a difference between events of types 1-3 and events of type 4 and 5, when event 1, 2, and 3 happen can be determined before the simulation starts for every customer, while the times for events 4 and 5 depend on the other customers and the checkouts
*   Use print/log statements to make a log of what happes.
*   java.util.Random has a constructor that takes a seed as a parameter. Given the same seed Random will always generate the same sequence of numbers. Use it to repeat a test run exactly.

### Online resources

*   [https://en.wikipedia.org/wiki/Discrete_event_simulation](https://en.wikipedia.org/wiki/Discrete_event_simulation)

### Hand in

Hand in your programming project as Github repository. The deadline is specified in the course plan and in the hand-in folder.