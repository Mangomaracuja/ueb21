package ueb21;

/**
 *
 * @author manuel
 */
public class ExpressionTree {
    private final TreeNode head;
    private HashTabelle<String, Integer> table;
    
    public ExpressionTree(){
        this.head = null;
    }
    
    /**
     * Generiert einen Baum mithilfe der Hashtabelle
     * 
     * @param ausdruck
     * @param tb 
     */
    public void generateTree(String ausdruck, HashTabelle tb) {
        this.table = tb;
    }
    
    /**
     * Wertet den im Tree stehenden Ausdruck aus und gitb das Ergebnis zurück.
     * 
     * @return 
     */
    public double calculate(){
        return 0.0;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return null;
    }
}
