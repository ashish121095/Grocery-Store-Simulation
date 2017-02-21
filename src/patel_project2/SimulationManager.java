/**
 * Project 2 for CS 1181. The purpose of this project is to create grocery store
 * simulation. The goal is to use simulation to analyze the customer’s shopping
 * experience given a number of express (12 items or less) checkout lanes, and a
 * number of checkout lanes serving any size order. This class contains main
 * method.
 */
package patel_project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Ashish Patel 
 * CS 1181-section05
 * Instructor: R.Volkers 
 * TA: Ryan Brant
 */
public class SimulationManager {

    /**
     * This method creates LoadData class object to test all methods and run the
     * program.
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        //Creates LoadData class object
        LoadData mydata = new LoadData();

        //Path to the text file
        String path = "CustomerArrivals.txt";
        Scanner scan = new Scanner(new File("CheckoutLanes.txt"));

        try {

            //load data from CustomerArrivals.txt file
            mydata.loadDataFromCustomerFile(path);

            //Creates object of CheckOutLanes class and reads data from 
            //CheckoutLanes.txt file
            CheckOutLanes[] checkOutLanes = mydata.getCheckOutLanes(scan);

            //Creates object of CheckOutLanes class and reads data from 
            //CheckOutLanes.txt file
            CheckOutLanes[] expressCheckOutLanes = mydata.getCheckOutLanes(scan);

            int j = 0;

            //Executes this loop until all data gets read from text file
            while (!LoadData.listCustomer.Empty()) {

                //dequeue process
                Customer customer = LoadData.listCustomer.deque();

                //condition true if CheckOutDoneTime is zero
                if (customer.getCheckOutDoneTime() == 0) {

                    //condition if total items is greater than 12 then it it goes 
                    //in regular checkOutLanes
                    if (customer.getNumberOfItems() > 12) {
                        checkOutLanes = mydata.shoppingDoneEvent(customer, checkOutLanes);
                    } //else goes in express Lanes
                    else {
                        expressCheckOutLanes = mydata.shoppingDoneEvent(customer, expressCheckOutLanes);
                    }
                } else {

                    //condition if total items is greater than 12 then it it goes 
                    //in regular checkOutLanes
                    if (customer.getNumberOfItems() > 12) {
                        checkOutLanes = mydata.checkOutDoneEvenet(customer, checkOutLanes);
                    } else {

                        //else goes in express Lanes
                        expressCheckOutLanes = mydata.checkOutDoneEvenet(customer, expressCheckOutLanes);
                    }
                }
                j++; //Increament J so, we can know how many customer is processed
            }
            System.out.println("\nprocessed : " + j + "\n");

            System.out.println("Customers Serviced \t" + "Max Line Length\t" + "\tAverage Line Length\t\t"
                    + "Average customer wait\n");

            //loop test to print regular checkOutLanes
            for (int i = 0; i < checkOutLanes.length; i++) {
                System.out.println("Reg: " + checkOutLanes[i].toString());
            }

            //loop test to print expressCheckOutLanes
            for (int i = 0; i < expressCheckOutLanes.length; i++) {
                System.out.println("Exp: " + expressCheckOutLanes[i].toString());
            }

        } catch (ItemAlreadyInListException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
