/*
 * Project 2 for CS 1181.
 * This class extends Exception.
 */
package patel_project2;

/**
 * @author Ashish patel 
 * CS1181L-section05 
 * Instructor: R. Volkers 
 * TA: Ryan Brant
 */
public class ItemNotFoundException extends Exception{
    
    /**
     * Constructor creates no-argument constructor.
     */
    ItemNotFoundException(){
        
        super();
    }
    
    /**
     * MyException throws an exception when an error occurs with proper message.
     * @param message
     */
    ItemNotFoundException(String message){
        
        super(message);
    }
     
}
