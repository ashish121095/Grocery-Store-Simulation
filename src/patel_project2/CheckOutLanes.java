/**
 * Project 2 for CS 1181. This class contains getters and setters method of
 * total time of processing customer, checkout time per item, customer payment
 * process time, customer service time, maximum waiting time, customer waiting
 * time, and total customer waiting time in line It also has add and remove
 * method to add customer in the line and remove from the line.
 */
package patel_project2;

import java.util.ArrayList;

/**
 * @author Ashish Patel
 * CS 1181-section05
 * Instructor: R.Volkers
 * TA: Ryan Brant
 */
public class CheckOutLanes {

    //Member  variables
    private double checkOutTimePerItem;
    private double timeToProcessCustomerPayment;
    private int customerServiced;
    private int customerWaiting;
    public int maximumWaitLine;
    public int runningTotalOfWaitingCustomer;
    public double runningTotalTimeOfProcessedCustomer;
    public double nextAvailableStartTime;

    /**
     * This method gets next available start time
     *
     * @return the double value of next available start time
     */
    public double getNextAvailableStartTime() {
        return nextAvailableStartTime;
    }

    /**
     * This method sets thee value for next available start time
     *
     * @param nextAvailableStartTime contains the value of next available start
     * time
     */
    public void setNextAvailableStartTime(double nextAvailableStartTime) {
        this.nextAvailableStartTime = nextAvailableStartTime;
    }

    //creates an arraylist
    private ArrayList<Customer> lineList;

    /**
     * no-argument constructor
     */
    public CheckOutLanes() {

        maximumWaitLine = 0;
        runningTotalOfWaitingCustomer = 0;
        runningTotalTimeOfProcessedCustomer = 0;
        customerWaiting = 0;
        customerServiced = 0;
        nextAvailableStartTime = 0.0;
        lineList = new ArrayList<Customer>();

    }

    /**
     * This method gets total time of processed customer
     *
     * @return running total time of processed customer
     */
    public double getRunningTotalTimeOfProcessedCustomer() {
        return runningTotalTimeOfProcessedCustomer;
    }

    /**
     * This method sets the value of running total time of processed customer
     *
     * @param runningTotalTimeOfProcessedCustomer contains value of running
     * total time of processed customer
     */
    public void setRunningTotalTimeOfProcessedCustomer(
            double runningTotalTimeOfProcessedCustomer) {
        this.runningTotalTimeOfProcessedCustomer += runningTotalTimeOfProcessedCustomer;
    }

    /**
     * This method gets spend time on each item.
     *
     * @return time spend on each item
     */
    public double getCheckOutTimePerItem() {
        return checkOutTimePerItem;
    }

    /**
     * This method sets spend time value per item
     *
     * @param checkOutTimePerItem contains spend time value
     */
    public void setCheckOutTimePerItem(double checkOutTimePerItem) {
        this.checkOutTimePerItem = checkOutTimePerItem;
    }

    /**
     * This method gets customer payment process time
     *
     * @return payment process time
     */
    public double getTimeToProcessCustomerPayment() {
        return timeToProcessCustomerPayment;
    }

    /**
     * This method sets payment process time value
     *
     * @param timeToProcessCustomerPayment contains value for payment process
     * time
     */
    public void setTimeToProcessCustomerPayment(double timeToProcessCustomerPayment) {
        this.timeToProcessCustomerPayment = timeToProcessCustomerPayment;
    }

    /**
     * This method gets value of serviced customer
     *
     * @return customer serviced
     */
    public int getCustomerServiced() {
        return customerServiced;
    }

    /**
     * This method sets customer serviced time
     *
     * @param customerServiced contains value of customer serviced time
     */
    public void setCustomerServiced(int customerServiced) {
        this.customerServiced += customerServiced;
    }

    /**
     * This method gets the customer waiting time in a line
     *
     * @return customer waiting time
     */
    public int getCustomerWaiting() {
        return customerWaiting;
    }

    /**
     * This method sets customer waiting time in a line
     *
     * @param customerWaiting contains value of customer waiting time
     */
    public void setCustomerWaiting(int customerWaiting) {
        this.customerWaiting += customerWaiting;
        setMaximumWaitLine();
    }

    /**
     * This method gets the maximum wait line time
     *
     * @return max wait line
     */
    public int getMaximumWaitLine() {
        return maximumWaitLine;
    }

    /**
     * This method finds max wait line by finding customer waiting time then,
     * sets customer waiting time to maximum wait line.
     */
    public void setMaximumWaitLine() {

        if (customerWaiting > maximumWaitLine) {
            this.maximumWaitLine = customerWaiting;
        }
    }

    /**
     * This method gets customer total average waiting time
     *
     * @return number of waiting customer in a line
     */
    public int getTotalCustWaitingForAvgLine() {
        return runningTotalOfWaitingCustomer;
    }

    /**
     * This method sets customer total average waiting time
     */
    public void setTotalCustWaitingForAvgLine() {
        this.runningTotalOfWaitingCustomer += customerWaiting;
    }

    /**
     * This method adds customer in the line. It contains Customer object and
     * waiting time as an argument
     *
     * @param customer contains Customer object
     * @param waitingTime contains double value
     * @return double value of next available time
     */
    public double addCustomerToLine(Customer customer, double waitingTime) {

        //Invokes set customer total average waiting time and customer waiting time
        setTotalCustWaitingForAvgLine();
        setCustomerWaiting(1);

        if (customerWaiting == 1) {
            setNextAvailableStartTime(customer.getShoppingDoneTime() + waitingTime);
        } else {
            setNextAvailableStartTime(nextAvailableStartTime + waitingTime);
        }

        customer.setCheckOutDoneTime(nextAvailableStartTime);

        //add customer in the list
        lineList.add(customer);

        return nextAvailableStartTime;
    }

    /**
     * This method removes customer from the line as soon as checkout time done
     *
     * @param customer passes as a object
     */
    public void customerDone(Customer customer) {

        //invokes set methods
        setCustomerServiced(1);
        setCustomerWaiting(-1);

        //counts average waiting time
        double avgWaitingTime = customer.getCheckOutDoneTime() - customer.getShoppingDoneTime()
                - ((customer.getNumberOfItems() * checkOutTimePerItem) + timeToProcessCustomerPayment);

        //set average waiting time to running total time of processed customer 
        setRunningTotalTimeOfProcessedCustomer(avgWaitingTime);

        //removes the customer from the list
        lineList.remove(customer);

        System.out.println(customer.toString());

    }

    /**
     * This method simply prints all information
     *
     * @return String with information to print to the console
     */
    @Override
    public String toString() {
        return customerServiced + "        \t\t" + maximumWaitLine + "  \t\t" + (double) runningTotalOfWaitingCustomer / customerServiced
                + "\t\t " + runningTotalTimeOfProcessedCustomer / customerServiced;
    }
}
