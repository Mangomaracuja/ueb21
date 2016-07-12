package ueb21;

/**
 *
 * @author manuel
 * @param <T>
 * @param <K>
 */
public class HashElement<T, K> {
    private T wert;
    private K key;
    
    public HashElement(T wert, K key){
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
    public String toString(){
        return null;
    }
}
