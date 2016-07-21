/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author manuel
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
