
import ueb21.TreeNode;
import ueb21.HashTabelle;

/**
 * Created by niklasreinhard on 18/07/16.
 */
public class ExpressionTree<T> {
    
    private HashTabelle table;

    /*

    Sample usage:

     ExpressionTree<String> expTree = new ExpressionTree<String>(new TreeNode("Test"));
        expTree.insertLeft(expTree.getRoot(), new TreeNode("Test2"));
        expTree.insertRight(expTree.getRoot(), new TreeNode("Test3"));
        expTree.printInOrder(expTree.getRoot());

     */
    
    private TreeNode<T> root;

    public ExpressionTree(TreeNode<T> root) {
        
        this.root = root;
    }

    public TreeNode<T> getRoot(){
        return this.root;
    }
    /* inserts new TreeNode 'left' as left child of node*/
    /* returns left TreeNode*/
    public TreeNode<T> insertLeft(TreeNode<T> node, TreeNode<T> left){
        node.setLeft(left);
        return left;
    }
    /* inserts new TreeNode 'right' as right child of node*/
    /* returns right TreeNode*/
    public TreeNode<T> insertRight(TreeNode<T> node, TreeNode<T> right){
        node.setRight(right);
        return right;
    }

    /* prints Tree inOrder left-root-right*/
    public void printInOrder(TreeNode localRoot){
        if (localRoot != null) {
            printInOrder(localRoot.getLeft());
            System.out.println(localRoot.toString());
            printInOrder(localRoot.getRight());
        }
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
}
