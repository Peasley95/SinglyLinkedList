import java.util.*;
/**
 * Generic class SinglyLinkedList
 *
 * @author Alex Peasley
 * @version 28/11/17
 */
public class SinglyLinkedList<E> extends AbstractSequentialList<E>
{
    /** 
    * I have created fields to hold the first and the last nodes.
    * 
    * I have also created a field to count the number of nodes
    * realtime as they are created
    */
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Setting the first and last nodes to null so that we have effectively an empty list
     * with 2 elements was the easiest way in which to implement this empty constructor.
     * 
     * It didn't make sense to do anything with the counter until the nodes were actually 
     * populated and so I have initialised it's value as zero.
     */
    public SinglyLinkedList()
    {
        head = null;
        tail = null;
        size = 0;
    }
    
    public SinglyLinkedList(Collection<E> collection)
    {
       /** 
       * 1b. I wrote all the code for this constructor then when I moved onto write the add method I realised 
       * that the code was identical so decided to just call that instead
       */
       head = null;
       tail = null;
       size = 0;
       for (E item: collection) {
           add(item);
       }
    }
    
    public Node<E> getHead() {
        return head;
    }
    
    public Node<E> getTail() {
        return tail;
    }
    
    /*
     * I have created this method so that I can access the size variable
     * within the iterator
     */
    public void incrementSize() {
        size++;
    }
    
    public Node<E> setHead(Node<E> node) {
        head = node;
        return head;
    }
    
    public Node<E> setTail(Node<E> node) {
        tail = node;
        return tail;
    }
    
    public boolean isEmpty()
    {
        if (size() == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    // 1b. This method produces an instance of the SinglyListIterator class and returns it
    public ListIterator<E> listIterator(int index)
    {
        // Create a new Iterator using the index to start from and this
        // current list as instance variables
        SinglyListIterator<E> it = new SinglyListIterator<E>(index, this);
        return it;
    }
    
    
    /**
     * A test method that I wrote to check out the previous and next methods
     */
    public void iteratortest(int index) {
        listIterator(index).previous();
        listIterator(index).next();
    }
    
    /**
    * 1c. I have implemented this method by keeping a track of the elements as they are created
    * using the elementsCount variable and returning that variable in the method.
    * As I am not deleting any elements nothing further needs to be done
    */
    public int size() {
        return size;
    }
    
    /**
    * 1d. This will need testing but I believe that the use of the
    * setRight method should work nicely
    * 
    * Will I need to rewrite this to use the iterator? Probably
    * Then this code will move into my collection constructor.
    * I think that's what he wants, who fucking knows with Steffan though
    */
    public boolean add(E elem) {
       if(isEmpty()) //Case 1: List is empty so the new node becomes the head
       {
           head = new Node(elem, tail);
       } else if(size == 1) // Case 2: There is only a head node so make the new node the tail and make the head point to the tail
       {
           tail = new Node(elem, null);
           head.setRight(tail);
       } else // Case 3: List is larger than 1 and so the new node is added to the end, the old tail points to it, and the new node becomes the tail 
       {
           // Create the new node
           Node<E> node = new Node(elem, null);
           // Change the right pair object in the tail to point at the new node and not null
           tail.setRight(node);
           // Move the tail ponter to the new node
           tail = node;
       }
       // One and only one element is added per call so incrementing the size field here is sufficient   
       incrementSize();
       return true;
    }
}

