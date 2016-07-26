package ueb21;

/**
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 * @param <T>
 * @param <K>
 */
public class HashElement<K, T> {

    private T wert;
    private K key;

    public HashElement(K key, T wert) {
        this.wert = wert;
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getWert() {
        return wert;
    }

    public void setWert(T wert) {
        this.wert = wert;
    }

    @Override
    public String toString() {
        return "{" + key + ": :" + wert + "}";
    }
}
