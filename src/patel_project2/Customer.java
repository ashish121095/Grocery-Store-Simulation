/**
 * Project 2 for CS 1181. This class contains getters and setters method for
 * number of items, shopping done time, time to buy each item,checkoutDoneTime
 * and checkOutLanes.
 */
package patel_project2;

/**
 * @author Ashish Patel
 * CS 1181-section05 
 * Instructor: R.Volkers
 * TA: Ryan Brant
 */
public class Customer {

    //variables
    private double arrivalTime;
    private int numberOfItems;
    private double timeToBuyEachItem;
    private double shoppingDoneTime;

    /**
     * This method gets the shopping done time.
     *
     * @return shoppingDoneTime
     */
    public double getShoppingDoneTime() {
        return shoppingDoneTime;
    }

    /**
     * This method sets the value for shopping done time
     *
     * @param shoppingDoneTime contains value of shopping done time
     */
    public void setShoppingDoneTime(double shoppingDoneTime) {
        this.shoppingDoneTime = arrivalTime + shoppingDoneTime;
    }

    //variables
    private double checkOutDoneTime;
    private int checkOutLane;

    /**
     * Argument constructor
     *
     * @param arrivalTime
     * @param numberOfItems
     * @param timeToBuyEachItem
     */
    public Customer(double arrivalTime, int numberOfItems, double timeToBuyEachItem) {

        this.arrivalTime = arrivalTime;
        this.numberOfItems = numberOfItems;
        this.timeToBuyEachItem = timeToBuyEachItem;
        shoppingDoneTime = 0.0;
        checkOutDoneTime = 0.0;

    }

    /**
     * This method gets arrival time
     *
     * @return arrival time
     */
    public double getArrivalTime() {
        return arrivalTime;
    }

    /**
     * This method sets the value for arrival time
     *
     * @param arrivalTime contains arrival time
     */
    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * This method gets the number of items
     *
     * @return number of items
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * This method sets the value for number of items
     *
     * @param numberOfItems contains value of number of items
     */
    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    /**
     * This method gets time to buy each single item for user
     *
     * @return time spent to buy each item.
     */
    public double getTimeToBuyEachItem() {
        return timeToBuyEachItem;
    }

    /**
     * This method sets the value to time to buy each item
     *
     * @param timeToBuyEachItem contains value of time to buy each item
     */
    public void setTimeToBuyEachItem(double timeToBuyEachItem) {
        this.timeToBuyEachItem = timeToBuyEachItem;
    }

    /**
     * This method gets checkout done time
     *
     * @return check out time
     */
    public double getCheckOutDoneTime() {
        return checkOutDoneTime;
    }

    /**
     * This method sets the value for checkout done time
     *
     * @param checkOutDoneTime contains checkout time
     */
    public void setCheckOutDoneTime(double checkOutDoneTime) {
        this.checkOutDoneTime = checkOutDoneTime;
    }

    /**
     * This method gets the checkout lanes
     *
     * @return checkoutLane
     */
    public int getCheckOutLane() {
        return checkOutLane;
    }

    /**
     * This method sets the value for checkout lane.
     *
     * @param checkOutLane contains checkout lane value
     */
    public void setCheckOutLane(int checkOutLane) {
        this.checkOutLane = checkOutLane;
    }

    /**
     * This method simply prints all necessary information.
     *
     * @return String with information to print to the console
     */
    @Override
    public String toString() {

        return " Customer{ oSize = " + numberOfItems + ", itemTime = " + timeToBuyEachItem + ", arrivedAt: "
                + arrivalTime + ", shoppingDone: " + shoppingDoneTime + ", checkOutDone: " + checkOutDoneTime + " }";
    }
}
