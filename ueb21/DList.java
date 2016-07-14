package ueb21;

import Exceptions.DListException;

/**
 * Klasse DList. Verwaltet eine doppelt verkettete Liste mit Personen.
 *
 * @author Manuel Jung; Alexander Stolz
 * @param <T>
 */
public class DList<T> {

    private static final String MSG_LIST_EMPTY = "Liste ist leer!";
    private static final String MSG_INDEX_RANGE = "Der index ist nicht im zulässigem Bereich!";
    private static final String MSG_NULL_REFERENZE = "Es Wurde eine null-Referenz übergeben!";
    private static final String MSG_ALLREADY_EXISTS = "Name bereits in der Liste enthalten!";
    private static final String MSG_NOT_IN_LIST = "Objekt nicht in Liste enthalten";

    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    /**
     * Konstruktor der Klasse PersonenListe, mit initialisierung der einzelnen
     * Attribute.
     *
     */
    public DList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * gibt die Referenz auf das index-te Element zurück
     *
     * @param index Der Index des Element, welches zurückgegeben Werden soll.
     */
    private ListNode<T> entry(int index) throws DListException {
        if (size == 0) {
            throw new DListException(MSG_LIST_EMPTY);
        }
        if (index < 0 || index >= size) {
            throw new DListException(MSG_INDEX_RANGE);
        }

        ListNode<T> pn = head;

        for (int i = 0; i < index; i++) {
            pn = pn.getNext();
        }

        return pn;
    }

    /**
     * Fügt das Element an die index-te Stelle ein.
     *
     * @param index
     * @param neu
     * @throws Exceptions.DListException
     */
    public void addIndex(int index, T neu) throws DListException {
        if (neu == null) 
            throw new DListException(MSG_NULL_REFERENZE);
        if (index < 0 || index > size)
            throw new DListException(MSG_INDEX_RANGE);
        
        ListNode<T> pn;
        ListNode<T> newpn = new ListNode<>(neu, null, null);

        if (head == null && tail == null) {
            head = newpn;
            tail = newpn;
        } else if (index == size) {
            tail.setNext(newpn);
            newpn.setPrev(tail);
            tail = newpn;
        } else if (index == 0) {
            head.setPrev(newpn);
            newpn.setNext(head);
            head = newpn;
        } else {
            pn = entry(index);
            pn.getPrev().setNext(newpn);
            newpn.setPrev(pn.getPrev());
            pn.setPrev(newpn);
            newpn.setNext(pn);
        }
        size++;
    }

    /**
     * Wert in die Liste einfuegen
     *
     * @param neu Einzifügendes Element.
     * @throws Exceptions.DListException
     */
    public void add(T neu) throws DListException {
        if (neu == null) {
            throw new DListException(MSG_NULL_REFERENZE);
        }
        if (contains(neu)) {
            throw new DListException(MSG_ALLREADY_EXISTS);
        }
        
        addIndex(size, neu);
    }

    /**
     * Komplette Liste loeschen
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Enthaelt die Liste das Objekt?
     *
     * @param t
     * @return
     * @throws Exceptions.DListException
     */
    public boolean contains(T t) throws DListException {
        if (t == null)
            throw new DListException(MSG_NULL_REFERENZE);
        
        ListNode<T> pn = head;

        for (int i = 0; i < size; i++) {
            if (pn.getItem().equals(t)) {
                return true;
            }
            pn = pn.getNext();
        }

        return false;
    }

    /**
     * Objekt an der Stelle index zurueckgeben
     *
     * @param index Stelle an dem das Objekt zu suchen ist.
     * @return Elemetn welches zu suchen war.
     * @throws Exceptions.DListException
     */
    public T get(int index) throws DListException {
        if (size == 0) 
            throw new DListException(MSG_LIST_EMPTY);
        if (index < 0 || index >= size)
            throw new DListException(MSG_INDEX_RANGE);
        
        return entry(index).getItem();
    }

    /**
     * Wert des ersten Elements zurueckgeben
     *
     * @return Erstes Element.
     * @throws Exceptions.DListException
     */
    public T getFirst() throws DListException {
        if (size == 0) 
            throw new DListException(MSG_LIST_EMPTY);
        
        return head.getItem();
    }

    /**
     * Wert des letzten Elements zurueckgeben
     *
     * @return Letztes Element.
     * @throws Exceptions.DListException
     */
    public T getLast() throws DListException {
        if (size == 0)
            throw new DListException(MSG_LIST_EMPTY);
        return tail.getItem();
    }

    /**
     * An welcher Stelle steht das Objekt?
     *
     * @param t
     * @return den Index des Objektes oder -1
     * @throws Exceptions.DListException
     */
    public int indexOf(T t) throws DListException {
        if (size == 0)
            throw new DListException(MSG_LIST_EMPTY);
        if (t == null)
            throw new DListException(MSG_NULL_REFERENZE);
        if (!contains(t)) {
            throw new DListException(MSG_NOT_IN_LIST);
        }
        ListNode<T> pn = head;

        for (int i = 0; i < size; i++) {
            if (pn.getItem().equals(t)) {
                return i;
            }
            pn = pn.getNext();
        }

        return -1;
    }

    /**
     * allgemeine private Methode zum Entfernen eines Elements
     *
     * @param pn zu loeschendes Listenelement
     */
    private void remove(ListNode<T> pn) throws DListException {
        if (pn == null) 
            throw new DListException(MSG_NULL_REFERENZE);
        
        if (pn == head) {
            head.getNext().setPrev(null);
            head = head.getNext();
        } else if (pn == tail) {
            tail.getPrev().setNext(null);
            tail = tail.getPrev();
        } else {
            pn.getPrev().setNext(pn.getNext());
            pn.getNext().setPrev(pn.getPrev());
        }
        size--;
    }

    /**
     * Lösche Element an der Stelle index
     *
     * @param index Der Index dessen zugehöriges Objekt zu löschen ist.
     * @throws Exceptions.DListException
     */
    public void remove(int index) throws DListException {
        if (size == 0)
            throw new DListException(MSG_LIST_EMPTY);
        if (index < 0 || index >= size)
            throw new DListException(MSG_INDEX_RANGE);
        
        if (head == tail) {
            head = null;
            tail = null;
            size--;
        } else {
            ListNode<T> ln;
            ln = entry(index);
            remove(ln);
        }
    }

    /**
     * Entferne Element mit einem bestimmten Inhalt
     *
     * @param t
     * @throws Exceptions.DListException
     */
    public void remove(T t) throws DListException {
        if (size == 0)
            throw new DListException(MSG_LIST_EMPTY);
        if (t == null) 
            throw new DListException(MSG_NULL_REFERENZE);
        remove(indexOf(t));
    }

    /**
     * Das erste Element löschen
     * @throws Exceptions.DListException
     */
    public void removeFirst() throws DListException {
        if (size == 0)
            throw new DListException(MSG_LIST_EMPTY);
        remove(0);
    }

    /**
     * Das letzte Element löschen
     * @throws Exceptions.DListException
     */
    public void removeLast() throws DListException {
        if (size == 0)
            throw new DListException(MSG_LIST_EMPTY);
        remove(size-1);
    }

    /**
     * Gibt die Länge der Liste zurück;
     *
     * @return Anzahl der Elemente
     */
    public int size() {
        return size;
    }

    /**
     * ToString Methode gibt die gesamte Liste mit Ihren Inhalten aus.
     *
     * @return Ausgabe.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Elemente in der Liste:\n");
        sb.append("===================================\n");
        ListNode<T> pn = head;
        
        for (int i = 0; i < size; i++) {
            sb.append(String.format("%2d: %s", i, pn.getItem().toString()));
            pn = pn.getNext();
        }
        
        sb.append("===================================\n");
        
        return sb.toString();
    }
}
