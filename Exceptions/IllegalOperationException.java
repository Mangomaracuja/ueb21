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
