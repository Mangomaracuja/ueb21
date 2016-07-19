package ueb21;

/**
 * Created by niklasreinhard on 18/07/16.
 */
public class TreeNode<T> {
    private T key;
    private TreeNode left;
    private TreeNode right;


    public TreeNode(T key) {
        this.key = key;
    }

    public TreeNode(T key, TreeNode left, TreeNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public TreeNode() {
    }

    public T getKey() {
        return key;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setKey(T key){
        this.key = key;
    }

    public void setLeft(TreeNode<T> left){
        this.left = left;
    }

    public void setRight(TreeNode<T> right){
        this.right = right;
    }

    /* checks if current TreeNode is Leaf*/
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
