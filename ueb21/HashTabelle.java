package ueb21;

/**
 *
 * @author manuel
 * @param <K>
 * @param <T>
 */
public class HashTabelle<K, T> {
    private final HashElement<K, T> tabelle[];
    
    public HashTabelle(int size){
        tabelle = new HashElement[size];
    }
    
    public void insert(K key, T wert){
        
    }
    
    public T get(K key){
        return null;
    }
    
    public void remove(K key){
        
    }
    
    public void clear(){
        
    }
    
    private int hashing(K key){
        return 0;
    }
    
    /**
     *
     * 
     * @return 
     */
    @Override
    public String toString(){
        return null;
    }
}
