package Exceptions;

/**
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 */
public class IdentifierException extends Exception {

    /**
     * Creates a new instance of <code>IdentifierException</code> without detail
     * message.
     */
    public IdentifierException() {
    }

    /**
     * Constructs an instance of <code>IdentifierException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IdentifierException(String msg) {
        super(msg);
    }
}
