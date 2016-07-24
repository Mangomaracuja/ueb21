package ueb21;

import Exceptions.DListException;
import Exceptions.IdentifierException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 * @param <K>
 * @param <T>
 */
public class HashTabelle<K, T> {
    private static final String MSG_NOSUCHELEMENT = " Element existiert nicht";
    private static final String MSG_ALREADYEXISTS = " Element existiert bereits";

    private final DList<HashElement<K, T>> tabelle[];

    public HashTabelle(int size){
        tabelle = new DList[size * 2];
        for(int i=0;i<tabelle.length;i++){
            tabelle[i] = new DList<>();
        }

    }
    
    public void insert(K key, T wert) throws DListException, IdentifierException{
        if(get(key) != null) 
            throw new IdentifierException(MSG_ALREADYEXISTS);
        int hash = hashing(key);
        tabelle[hash].add(new HashElement<>(key, wert));
    }

    public void insertValue(K key, T wert) throws IdentifierException{
        int hash = hashing(key);
        if(get(key) == null) 
            throw new IdentifierException(MSG_NOSUCHELEMENT);
        get(key).setWert(wert);
    }
    
    public void insertKey(K key) throws IdentifierException, DListException{
        insert(key, null);
    }

    public HashElement get(K key){
        int hash = hashing(key);
        if(!tabelle[hash].isEmpty()){
            for (HashElement<K,T> he : tabelle[hash]) {
            if(he.getKey().equals(key)){
               return he;
            }
          }
        }
        return null;
    }

    public T getValue(K key){
        int hash = hashing(key);
        T erg = null;

        for (HashElement<K,T> he : tabelle[hash]) {
            if(he.getKey().equals(key)){
                erg = he.getWert();
                return erg;
            }
        }
        return null;
    }
    
    public void remove(K key) throws DListException {
        int hash = hashing(key);
        for (HashElement<K, T> he : tabelle[hash]) {
            if (he.getKey().equals(key)) {
                tabelle[hash].remove(he);
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
