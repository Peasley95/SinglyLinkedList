import java.util.*;
/**
 * Generic class SinglyListIterator
 *
 * @author Alex Peasley
 * @version 30/11/2017
 */
public class SinglyListIterator<E> implements ListIterator<E> {
    // instance variables - replace the example below with your own

    private Node<E> previous;
    private Node<E> next;
    private int lookupIndex;
    private int nextIndex;
    private int previousIndex;
    private SinglyLinkedList list;

    /**
     * Constructor for objects of class SinglyListIterator
     */
    public SinglyListIterator(int index, SinglyLinkedList list) {
        assert (index >= 0 && index < list.size() && !list.isEmpty());
        previous = list.getHead();
        next = previous.getRight();
        // This for loop should move the cursor into the correct possition
        for (int i = 0; i < index - 1; i++) {
            previous = previous.getRight();
            if(i == index) {
                next = null;
            } else {
                next = previous.getRight();
            }
        }
        nextIndex = index + 1;
        previousIndex = index;
        lookupIndex = index;
        this.list = list;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void add(E e) {
        // Case 1: adding an element somewhere in the middle of the list
        if(hasNext() && hasPrevious()){
            Node<E> node = new Node(e, next);
            next = next.getRight();
            nextIndex++;
            previous.setRight(node);
        } 
        // Case 2: adding an element at the end of the list
        else if (!hasNext() && hasPrevious()) {
            Node<E> node = new Node(e,null);
            next = null;
            nextIndex++;
            previous.setRight(node);
            list.setTail(node);
        } 
        // Case 3: adding an element at the beginning of the list
        else if (hasNext() && !hasPrevious()) {
            Node<E> node = new Node(e, next);
            next = next.getRight();
            nextIndex++;
            node.setRight(list.getHead());
            list.setHead(node);
        }
        list.incrementSize();
    }

    public boolean hasNext() {
        // May need to run through all the cases if the fields are too hard to maintain 
        return nextIndex < list.size(); 
    }

    public boolean hasPrevious() {
        return previousIndex > 0;
    }

    public E next() {
        if (next == null)
            return null;
        /** 
         * Special case: when the pointer is off the list, previous will
         * be the next element
         */
        if(lookupIndex == 0) {
            System.out.println(previous.getLeft());
            return previous.getLeft();
        }

        System.out.println(next.getLeft());
        return next.getLeft();
    }

    /**
     * 2d. I decided that the most efficient way to do this was to move
     * the previous and next nodes to the beginning and move them 
     * down the list until they fall into their new positions.
     * 
     * This way it will never take longer than the length of the list
     * thus costing O(n)
     */
    public E previous() { 
        previous = list.getHead();
        previousIndex = 0;
        next = previous.getRight();
        nextIndex = 1;
        /**
         * I was going to check !isEmpty() but decided that the assert in
         * the constructor would be better.
         * 
         * I then checked returning the first element as a special case as
         * the pointers are already in the correct place
         */
        if(lookupIndex == 1) {
            System.out.println(previous.getLeft());
            return previous.getLeft();
        }
        //I then loop through advancing the nodes to the correct positions
        for(int i = 0;  i < lookupIndex - 1; i++) {
            previous = previous.getRight();
            previousIndex++;
            if(next.getRight() != null){                
                next = next.getRight();
            }
            nextIndex++;
        }
        /**
         * If previousIndex is still 0 then we are at the front of the list
         * and there is no previous element
         */ 
        if(previousIndex == 0){
            System.out.println("There are no elements before: " + previous.getLeft());
            return null;
        }    

        System.out.println(previous.getLeft());
        return previous.getLeft();
    }

    public int nextIndex() {
        return nextIndex;
    }

    public int previousIndex() {
        return previousIndex;
    }

    public void remove() {
        // Unimplemented remove method
    }

    public void set(E e) {
        // Unimplemented set method
    }
}
