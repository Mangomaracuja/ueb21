package Exceptions;

/**
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 */
public class NoValueInHashTableException extends Exception {

    /**
     * Creates a new instance of <code>NoValueInHashTableException</code>
     * without detail message.
     */
    public NoValueInHashTableException() {
    }

    /**
     * Constructs an instance of <code>NoValueInHashTableException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoValueInHashTableException(String msg) {
        super(msg);
    }
}
