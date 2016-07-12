package ueb21;

import java.io.File;

/**
 *
 * @author manuel
 */
public class Ueb21 {
    private ExpressionTree tree;
    private HashTabelle<String, Integer> table;

    private Ueb21(){
        this.tree = new ExpressionTree();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Ueb21().start(args);
    }
    
    private void start(String[] args){
        
    }
    
    private File getFile(String name){
        return null;
    }
    
    /**
     * Wertet die Datei aus.
     * 
     * @param file 
     */
    private void dateiAuswerten(File file) {
        
    }
    
    /**
     * Werte Die einzelnen Zeilen aus, wenn Identifier deklariert werden, werden
     * sie in die Tabelle eingetragen, wenn der ausdruck steht wird dieser mit der Tabelle
     * an den Expressiontree übergeben, wenn wertzuweisung dann werte in Tabelle eintragen.
     * Fehlermeldung werfen wenn bei wertzuweisung ein nicht deklarierter identifiert steht.
     * 
     * @param zeile 
     */
    private void zeileAuswerten(String zeile) {
        
    }
    
    /**
     * Zählt die Zeilen in denen die Identifier Deklariert werden und erstell
     * dann eine Hashtabelle mit passender Länge.
     * 
     * @param file 
     */
    private void tabelleErstellen(File file){
        
    }
    
    /**
     * Den Akuellen Baum Ausgeben. 
     * 
     */
    private void display(){
        
    }
}
