package ueb21;

/**
 * Klasse ListNode, Ein Listenelement einer doppelt verketteten Liste. Mit den
 * Attribten prev (Vorgänger), next (Nachfolger) und person Inhalt des
 * Listenelements.
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 * @param <T>
 */
public class ListNode<T> {

    private T item;
    private ListNode prev;
    private ListNode next;

    /**
     * Konstruktor der Klasse PersonenNode, mit initialisierung von den
     * Attributen.
     *
     * @param item
     * @param prev
     * @param next
     */
    public ListNode(T item, ListNode prev, ListNode next) {
        this.item = item;
        this.prev = prev;
        this.next = next;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public ListNode getPrev() {
        return prev;
    }

    public void setPrev(ListNode prev) {
        this.prev = prev;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    /**
     * ToString Methode, gibt den Inhalt dieses Nodes und den des Vor- und
     * Nachfolger.
     *
     * @return
     */
    @Override
    public String toString() {
        return null;
    }
}
