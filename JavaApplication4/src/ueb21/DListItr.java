package ueb21;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author manuel
 * @param <T>
 */
public class DListItr<T> implements Iterator{

    private ListNode<T> le;

    /**
     * Ein Iteratorobjekt erzeugen
     *
     * @param start das Listenelement mit dem gestartet werden soll
     */
    public DListItr(ListNode<T> start) {
        le = start;
    }

    /**
     * Gibt es ein nachfolgendes mElement?
     *
     * @return true, falls ja andernfalls false
     */
    @Override
    public boolean hasNext() {
        return le != null;
    }

    /**
     * Gehe zum nächsten Element weiter
     *
     * @return Referenz auf das nächste Element
     * @exception NoSuchElementException falls es kein nächstes Element
     *
     * mehr gibt
     */
    @Override
    public T next() throws NoSuchElementException {
        if (le == null) {
            throw new NoSuchElementException();
        }
        T t = le.getItem();
        le = le.getNext();
        return t;
    }

    /**
     * Diese Methode ist nicht implementiert
     *
     * @exception UnsupportedOperationException falls die Methode
     *
     * aufgerufen wird
     */
    @Override
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
