/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ueb21;

import Exceptions.StackException;
import java.lang.reflect.Array;

/**
 *
 * @author manuel
 * @param <T>
 */
public class Stack<T> {
    private static final String MSG_EMPTY = "Stack ist leer!";
    private static final String MSG_FULL = "Stack ist voll!";
    private final T[] stack;
    private int size;
    
    public Stack(Class clazz, int size){
        stack = (T[]) Array.newInstance(clazz, 2 );
        size = 0;
    }
    
    public void push(T t) throws StackException{
        if (full()) throw new StackException(MSG_FULL);
        stack[size] = t;
        size++;
    }
    
    public T pop() throws StackException{
        if(empty()) throw new StackException(MSG_EMPTY);
        size--;
        T t = stack[size];
        stack[size] = null;
        return t;
    }
    
    public T top(){
        return stack[size - 1];
    }
    
    public int size(){
        return size;
    }
    
    public boolean full(){
        return size == stack.length;
    }
    
    public boolean empty(){
        return size == 0;
    }
}
