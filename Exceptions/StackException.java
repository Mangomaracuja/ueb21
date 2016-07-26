package Exceptions;

/**
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 */
public class StackException extends Exception {

    /**
     * Creates a new instance of <code>StackException</code> without detail
     * message.
     */
    public StackException() {
    }

    /**
     * Constructs an instance of <code>StackException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public StackException(String msg) {
        super(msg);
    }
}
