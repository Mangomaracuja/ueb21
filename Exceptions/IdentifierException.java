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
