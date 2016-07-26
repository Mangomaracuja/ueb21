package Exceptions;

/**
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 */
public class IllegalOperationException extends Exception {

    /**
     * Creates a new instance of <code>IllegalOperationException</code> without
     * detail message.
     */
    public IllegalOperationException() {
    }

    /**
     * Constructs an instance of <code>IllegalOperationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IllegalOperationException(String msg) {
        super(msg);
    }
}
