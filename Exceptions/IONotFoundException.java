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
