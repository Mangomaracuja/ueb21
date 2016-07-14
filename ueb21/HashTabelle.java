package ueb21;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author manuel
 * @param <K>
 * @param <T>
 */
public class HashTabelle<K, T> {
    private static final String MSG_NOSUCHELEMENT = " Element existiert nicht";
    private static final String MSG_ALREADYEXISTS = " Element existiert nicht";

    private ArrayList<HashElement<K, T>> tabelle[];

    public HashTabelle(int size){
        tabelle = new ArrayList[size];
        for(int i=0;i<size;i++){
            tabelle[i] = new ArrayList<HashElement<K,T>>();
        }

    }
    
    public void insert(K key, T wert){
        int hash = hashing(key);
        tabelle[hash].add(new HashElement<K, T>(key, wert));
    }

    public void insertValue(K key, T wert){
        int hash = hashing(key);
        if(get(key) == null) {
            //exception ELementAlreadyExists
        }
        get(key).setWert(wert);
    }

    public HashElement get(K key){
        int hash = hashing(key);

        for (HashElement<K,T> he: tabelle[hash]) {
            if(he.getKey().equals(key)){
               return he;
            }
        }
        return null;
    }

    public T getValue(K key){
        int hash = hashing(key);
        T erg = null;

        for (HashElement<K,T> he: tabelle[hash]) {
            if(he.getKey().equals(key)){
                erg = he.getWert();
                return erg;
            }
        }
        return null;
    }
    
    public void remove(K key) {
        int hash = hashing(key);
        for (HashElement<K, T> he : tabelle[hash]) {
            if (he.getKey().equals(key)) {
                tabelle[hash].remove(key);
                return;
            }
        }
        throw new NoSuchElementException(key + MSG_NOSUCHELEMENT);
    }
    
    public void clear(){
        for(int i=0;i<tabelle.length;i++){
            tabelle[i] = null;
        }
    }
    
    public int hashing(K key){
        String str = (String) key.toString();
        int hash = 0;

        for (int i=0;i<str.length();i++){
            hash += str.charAt(i) * i;
        }

        hash = hash % tabelle.length;

        return hash;
    }
    
    /**
     *
     * 
     * @return 
     */
    @Override
    public String toString(){
       return super.toString();
    }
}
