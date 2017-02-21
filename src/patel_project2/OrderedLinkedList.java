/*
 * Project 2 for CS 1181.
 * The purpose of this lab is to create own data structure based on linked list
 * which implements all neccessary methods including insert, remove, listCount, 
 * value, get value at position, dequeue. 
 * It also has toString method to print innformation.
 */
package patel_project2;

/**
 * @author Ashish patel 
 * CS1181L-section05 
 * Instructor: R. Volkers
 * TA: Ryan Brant
 *
 * @param <T>
 *
 */
public class OrderedLinkedList<T> {

    private int listCount = 0;

    /**
     * This class creates contains global member variables and create
     * constructor for creating node.
     *
     * @param <T>
     */
    private class Node<T> {

        public T payLoad;
        public double keyValue;
        int numOfElements;
        public Node next;

        /**
         * This is a constructor which creates node with value of payload and
         * key value
         *
         * @param payload contains user wished value of any type
         * @param value contains key value.
         */
        public Node(T payload, double value) {

            this.payLoad = payload;
            this.keyValue = value;
            this.next = null;
        }
    }

    private Node<T> first, temp;

    /**
     * This boolean method checks the list is empty or not.
     *
     * @return true if list is empty and false if list is not empty.
     */
    public boolean Empty() {

        if (first == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method insert node as a sorted list.
     *
     * @param payLoad contains user-wished value
     * @param key contains key value
     * @throws ItemAlreadyInListException
     */
    public void insert(T payLoad, double key) throws ItemAlreadyInListException {

        Node<T> newNode = new Node<T>(payLoad, key);
        Node<T> temp1 = first;

        //add a nde at a first place
        if (first == null) {
            first = newNode;
        } else if (key < temp1.keyValue) {
            newNode.next = temp1;
            first = newNode;
            return;
        } else {
            Node<T> after = first.next;

            //loop test temp1 until it is null 
            while (after != null) {

                //add a node according to it's place.
                if (key < after.keyValue) {
                    break;
                }

                temp1 = after;
                after = temp1.next;
            }

            newNode.next = temp1.next;
            temp1.next = newNode;
        }
    }

    /**
     * This method remove a node from a proper place.
     *
     * @param key contains value for which node user wants to remove.
     * @throws ItemNotFoundException
     */
    public void remove(int key) throws ItemNotFoundException {

        Node<T> current = first;

        //check condition for empty list
        if (first == null) {
            throw new ItemNotFoundException("Trying to remove an item from empty list");
        }

        //move first to next node
        if (key == first.keyValue) {
            first = first.next;
            return;
        }

        //runs until the current is as close as possible to the key value entered
        while (current.keyValue < key - 1) {
            current = current.next;

        }
        if (current.keyValue <= key) {
            current.next = current.next.next;
        } else {
            throw new ItemNotFoundException("Item is not in the list.");
        }

        listCount--;
    }

    /**
     * This method counts how many items are in the linked list
     *
     * @return number of elements in the list.
     */
    public int listCount() {

        int count = 0;
        Node<T> temp1 = first;

        //while loop to check whole list 
        while (temp1 != null) {

            //increament count 
            count++;

            //move temp1 variable to the next node
            temp1 = temp1.next;
        }
        return count;
    }

    /**
     * This method gets user-entered value.
     *
     * @param key contains key value
     * @return user-wished value.
     * @throws ItemNotFoundException
     */
    public T getValue(double key) throws ItemNotFoundException {

        if (first == null) {
            throw new ItemNotFoundException("The list is empty.");
        }
        Node<T> temp1 = first;

        //while loop goes through the list to find desired value until the 
        //condition becomes false
        while (temp1.keyValue < key) {

            temp1 = temp1.next;
        }

        //consition if desired value is in the list then return that element
        if (temp1.keyValue == key) {
            return temp1.payLoad;
        } else {
            return null;
        }
    }

    /**
     * This method gets the value from specified position
     *
     * @param position contains specific position
     * @return value at a specific position
     */
    public T getValueAtPosition(int position) {

        //condition for position 0 then it returns first item's data
        if (position == 0) {
            return first.payLoad;
        }

        Node<T> temp = first;

        //loop test go through the whole list
        for (int i = 1; i <= position; i++) {
            temp = temp.next;
        }
        return temp.payLoad;
    }

    /**
     * THis method dequeue an element from the queue.
     *
     * @return removed element from the queue
     */
    public T deque() {

        Node<T> temp = first;

        first = first.next;
        return temp.payLoad;
    }

    /**
     * This method display all elements of the list in a single line.
     *
     * @return string
     */
    @Override
    public String toString() {
        Node<T> temp2 = first;
        if (listCount() == 0) {
            return "The list is empty.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("List contents:\n");

            System.out.println("total count: == " + listCount());
            // Traverse the list all the way to the end
            while (temp2 != null) {

                // add the current node's data to the string we are building
                sb.append(temp2.payLoad.toString());
                sb.append("\n");

                // advance to the next node in the list
                temp2 = temp2.next;
            }
            return sb.toString();
        }
    }
}
