
/**
 * general purpose pairs of objects
 * 
 * @author Stefan Kahrs
 * @version 21/09/2017
 */
public class Pair<A,B>
{
    /** fields are left non-private to allow for efficient
     ** manipulation of pair objects
     */
    protected A left;
    protected B right;

    /**
     * Constructor for objects of class Pair
     */
    public Pair(A left,B right)
    {
        this.left=left;
        this.right=right;
    }

    // selectors and mutators
    public A getLeft()  { return left;  }
    public B getRight() { return right; }
    public void setLeft(A newLeft) { left=newLeft; }
    public void setRight(B newRight) { right=newRight; }
    
    /** if pairs are used as 'ordinary' pairs this is fine,
     *  but often we should use a more bespoke String representation
     */
    public String toString() {
        return "(" + left + "," + right + ")";
    }
}
