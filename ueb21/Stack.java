package ueb21;

import Exceptions.StackException;
import java.lang.reflect.Array;

/**
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 * @param <T>
 */
public class Stack<T> {
    private static final String MSG_EMPTY = "Stack ist leer!";
    private static final String MSG_FULL = "Stack ist voll!";
    private final T[] stack;
    private int size;
    
    /**
     * Konstruktor mit Länge und Datentyp um das Array zu Initialisieren.
     * 
     * @param clazz .class des Types der zu verwalten ist.
     * @param size  integer für die Länge des Arrays
     */
    public Stack(Class clazz, int size){
        stack = (T[]) Array.newInstance(clazz, 2 );
        size = 0;
    }
    
    /**
     * Methode zum Einfügen eines Objektes in das Stack.
     * 
     * @param t Objekt welches einzufügen ist.
     * @throws StackException Exception, wenn Stack bereits voll,
     * da nichts mehr eingefügt werden kann.
     */
    public void push(T t) throws StackException{
        if (full()) throw new StackException(MSG_FULL);
        stack[size] = t;
        size++;
    }
    
    /**
     * Methode zum Entnehmen eines Objekte aus dem Stack.
     * 
     * @return das entnommene Objekt.
     * @throws StackException Exception, wenn Stack bereits leer, 
     * da nichts mehr entnommen werden kann.
     */
    public T pop() throws StackException{
        if(empty()) throw new StackException(MSG_EMPTY);
        size--;
        T t = stack[size];
        stack[size] = null;
        return t;
    }
    
    /**
     * Methode um das Oberste Objekt im Stack zurückzugeben ohne dieses
     * zu entnehmen.
     * 
     * @return Das Oberste Objekt im Stack.
     */
    public T top(){
        return stack[size - 1];
    }
    
    /**
     * Methode um die aktuelle Anzahl an Objekten im Stack zurückzugeben.
     * 
     * @return Akutelle Anzahl an Objekten.
     */
    public int size(){
        return size;
    }
    
    /**
     * Boolean Methode zum Prüfen ob das Stack voll ist.
     * 
     * @return True wenn voll.
     */
    public boolean full(){
        return size == stack.length;
    }
    
    /**
     * Boolean Methode zum Prüfen ob das Stack leer ist.
     * 
     * @return True wenn leer.
     */
    public boolean empty(){
        return size == 0;
    }
    
    /**
     * Standard toString Methode um den Akutellen Inhalt des Stack zurückzugeben.
     * 
     * @return String mit aktuellem Inhalt.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Inhalt im Stack:\n");
        for(int i = 0; i < size; i++){
            sb.append(stack[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
