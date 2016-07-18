/**
 * Created by niklasreinhard on 18/07/16.
 */
public class ExpressionTree<T> {

    /*

    Sample usage:

     ExpressionTree<String> expTree = new ExpressionTree<String>(new Node("Test"));
        expTree.insertLeft(expTree.getRoot(), new Node("Test2"));
        expTree.insertRight(expTree.getRoot(), new Node("Test3"));
        expTree.printInOrder(expTree.getRoot());

     */
    
    private Node<T> root;

    public ExpressionTree(Node<T> root) {
        this.root = root;
    }

    public Node<T> getRoot(){
        return this.root;
    }
    /* inserts new Node 'left' as left child of node*/
    /* returns left Node*/
    public Node<T> insertLeft(Node<T> node, Node<T> left){
        node.setLeft(left);
        return left;
    }
    /* inserts new Node 'right' as right child of node*/
    /* returns right Node*/
    public Node<T> insertRight(Node<T> node, Node<T> right){
        node.setRight(right);
        return right;
    }

    /* prints Tree inOrder left-root-right*/
    public void printInOrder(Node localRoot){
        if (localRoot != null) {
            printInOrder(localRoot.getLeft());
            System.out.println(localRoot.toString());
            printInOrder(localRoot.getRight());
        }
    }
}
