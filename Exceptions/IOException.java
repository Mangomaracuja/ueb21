package Exceptions;

/**
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 */
public class IOException extends Exception {

    /**
     * Creates a new instance of <code>IOException</code> without detail
     * message.
     */
    public IOException() {
    }

    /**
     * Constructs an instance of <code>IOException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public IOException(String msg) {
        super(msg);
    }
}
