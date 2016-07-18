/**
 * Created by niklasreinhard on 18/07/16.
 */
public class Node<T> {
    private T key;
    private Node left;
    private Node right;


    public Node(T key) {
        this.key = key;
    }

    public Node(T key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public Node() {
    }

    public T getKey() {
        return key;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setKey(T key){
        this.key = key;
    }

    public void setLeft(Node<T> left){
        this.left = left;
    }

    public void setRight(Node<T> right){
        this.right = right;
    }

    /* checks if current Node is Leaf*/
    public boolean isLeaf(){
        if(this.left == null && this.right == null){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
