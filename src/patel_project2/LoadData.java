/**
 * Project 2 for CS 1181. This class reads different data from text file and
 * store every value in specific initialized variables.
 */
package patel_project2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Ashish Patel
 * CS 1181-section05 
 * Instructor: R.Volkers 
 * TA: Ryan Brant
 */
public class LoadData {

    static OrderedLinkedList<Customer> listCustomer;

    /**
     * This method simply loads the data from text file
     *
     * @param path which is a contains text file's location
     * @return listCustomer with inserted value
     * @throws ItemAlreadyInListException
     * @throws IOException
     */
    public OrderedLinkedList<Customer> loadDataFromCustomerFile(String path) throws ItemAlreadyInListException, IOException {

        Scanner reader = null;
        try {

            //open File and read data from it using scanner 
            File file = new File(path);
            reader = new Scanner(file);
            String line = null;

            //creates an object of orderedLinkedList class
            listCustomer = new OrderedLinkedList<>();

            //checks file has next element in the file or not
            while (reader.hasNext()) {

                //read from file and store it in stat time
                double startTime = reader.nextDouble();

                //read integer from the file right after double values and 
                //stores in totalItems
                int totalItems = reader.nextInt();

                //read double values from the file right ater integer value and 
                //stores it in timeForEachItem
                double timeForEachItem = reader.nextDouble();

                //creates customer object of customer class 
                Customer customer = new Customer(startTime, totalItems, timeForEachItem);

                //insert all values in listCustomer 
                listCustomer.insert(customer, startTime + totalItems * timeForEachItem);
            }

            //close text file
            reader.close();

            return listCustomer;

        } catch (IOException ioException) {

            reader.close();
            ioException.printStackTrace();
            return null;
        }

    }

    /**
     * This method gets value of check out lanes
     *
     * @param scanner contains Scanner object
     * @return checkOutLanes with value in it
     */
    public CheckOutLanes[] getCheckOutLanes(Scanner scanner) {

        //reads integer value and stores it in numberOLanes
        int numberOfLanes = scanner.nextInt();

        //creates an array of CheckOutLanes class object with size numberOfItems
        CheckOutLanes[] checkOutLanes = new CheckOutLanes[numberOfLanes];

        //loop test to go through all elements
        for (int i = 0; i < numberOfLanes; i++) {

            //creates an object 
            checkOutLanes[i] = new CheckOutLanes();

            //read double value from file and sets it to CheckOutTimePerItem
            checkOutLanes[i].setCheckOutTimePerItem(scanner.nextDouble());

            //read double value from file and sets it to TimeToProcessCustomerPayment
            checkOutLanes[i].setTimeToProcessCustomerPayment(scanner.nextDouble());
        }

        return checkOutLanes;
    }

    /**
     * This method handles the shopping done event
     *
     * @param customer contains Customer class object
     * @param checkOutLanes
     * @return CheckoutLanes
     * @throws ItemAlreadyInListException
     */
    public CheckOutLanes[] shoppingDoneEvent(Customer customer, CheckOutLanes[] checkOutLanes) throws ItemAlreadyInListException {

        int myLan = 0;
        int myLanSize = Integer.MAX_VALUE;

        //loop test to go through whole checkOutLane
        for (int i = 0; i < checkOutLanes.length; i++) {

            // condition to check customer waiting time is less than the Max Value
            if (checkOutLanes[i].getCustomerWaiting() < myLanSize) {

                //myLan is equal to index
                myLan = i;

                //change myLanSize
                myLanSize = checkOutLanes[i].getCustomerWaiting();
            }

        }

        //sets checkout lane is equal to myLan
        customer.setCheckOutLane(myLan);

        //calculates shoppingDoneTime by multiplying number of item and time to buy 
        //each item
        double shoppingDoneTime = customer.getNumberOfItems() * customer.getTimeToBuyEachItem();

        //set shoppingDoneTime 
        customer.setShoppingDoneTime(shoppingDoneTime);

        //calculates orderProcessTime by multiplying number of items and time per 
        //item and by adding customer payment process
        double orderProcessTime = (customer.getNumberOfItems()
                * checkOutLanes[myLan].getCheckOutTimePerItem()) + checkOutLanes[myLan].getTimeToProcessCustomerPayment();

        //calculates checkOutDoneTime by adding customer in the line
        double checkOutDoneTime = checkOutLanes[myLan].addCustomerToLine(customer, orderProcessTime);

        //set checkoutDoneTime
        customer.setCheckOutDoneTime(checkOutDoneTime);

        //insert customer
        listCustomer.insert(customer, checkOutDoneTime);

        return checkOutLanes;
    }

    /**
     * This method handles the checkOutEvent done.
     *
     * @param customer contains Customer class object
     * @param checkOutLanes contains checkoutLanes array
     * @return checkOutLanes
     */
    public CheckOutLanes[] checkOutDoneEvenet(Customer customer, CheckOutLanes[] checkOutLanes) {

        checkOutLanes[customer.getCheckOutLane()].customerDone(customer);

        return checkOutLanes;

    }
}
