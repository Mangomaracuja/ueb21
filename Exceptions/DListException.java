package Exceptions;

/**
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 */
public class DListException extends Exception {

    /**
     * Creates a new instance of <code>DListException</code> without detail
     * message.
     */
    public DListException() {
    }

    /**
     * Constructs an instance of <code>DListException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DListException(String msg) {
        super(msg);
    }
}
