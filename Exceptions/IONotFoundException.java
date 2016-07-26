package Exceptions;

/**
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 */
public class IONotFoundException extends Exception {

    /**
     * Creates a new instance of <code>IONotFoundException</code> without detail
     * message.
     */
    public IONotFoundException() {
    }

    /**
     * Constructs an instance of <code>IONotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IONotFoundException(String msg) {
        super(msg);
    }
}
