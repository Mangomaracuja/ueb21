package ueb21;

import Exceptions.DListException;
import Exceptions.IdentifierException;
import java.util.NoSuchElementException;

/**
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 * @param <K>
 * @param <T>
 */
public class HashTabelle<K, T> {
    
    /* Eigene Exceptions */
    private static final String MSG_NOSUCHELEMENT = " Element existiert nicht";
    private static final String MSG_ALREADYEXISTS = " Element existiert bereits";

    
    private final DList<HashElement<K, T>> tabelle[];

    /* Konstruktur*/
    /* Size steht für die Anzahl der Plätze in der HashTabelle*/
    public HashTabelle(int size) {
        tabelle = new DList[size];
        for (int i = 0; i < tabelle.length; i++) {
            tabelle[i] = new DList<>();
        }

    }
    
    /* Fügt ein neues HashElement an entsprechender Stelle ein, wenn noch nicht existiert*/
    /* falls Element bereits vorhanden wird IdentifierException geworfen*/
    
    public void insert(K key, T wert) throws DListException, IdentifierException {
        if (get(key) != null) {
            throw new IdentifierException(MSG_ALREADYEXISTS);
        }
        int hash = hashing(key);
        tabelle[hash].add(new HashElement<>(key, wert));
    }

    /* fügt ein Value T zu HashElement mit Key K*/
    /* falls HashElement mit Key K nicht existiert, wird eine Identifier Exception geworfen*/
    
    public void insertValue(K key, T wert) throws IdentifierException {
        int hash = hashing(key);
        if (get(key) == null) {
            throw new IdentifierException(MSG_NOSUCHELEMENT);
        }
        get(key).setWert(wert);
    }

    /* Erstellt ein neues HashElement, das einen Key K enthält und Value null*/
    
    public void insertKey(K key) throws IdentifierException, DListException {
        insert(key, null);
    }

    /* Liefert das gefundene HashElement mit Key K zurück oder null, falls Element nicht existiert*/
    public HashElement get(K key) {
        int hash = hashing(key);
        if (!tabelle[hash].isEmpty()) {
            for (HashElement<K, T> he : tabelle[hash]) {
                if (he.getKey().equals(key)) {
                    return he;
                }
            }
        }
        return null;
    }

    /* Sucht das HashElement mit Key K und gibt das zugehörige Value T zurück*/
    public T getValue(K key) {
        int hash = hashing(key);
        T erg;

        for (HashElement<K, T> he : tabelle[hash]) {
            if (he.getKey().equals(key)) {
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

    /* Bereinigt die HashTabelle */
    public void clear() {
        for (int i = 0; i < tabelle.length; i++) {
            tabelle[i] = null;
        }
    }
    /* Berechnet einen HashWert für ein gegebenen Key K*/
    /* liefert errechneten HashWert zurück*/
    public int hashing(K key) {
        String str = (String) key.toString();
        int hash = 0;

        for (int i = 0; i < str.length(); i++) {
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (DList<HashElement<K, T>> list : tabelle) {
            if (!list.isEmpty()) {
                sb.append(hashing(list.getFirst().getItem().getKey()));
                sb.append(": ");
                for (HashElement<K, T> he : list) {
                    sb.append(" <-> ");
                    sb.append(he.toString());
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
