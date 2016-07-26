package ueb21;

/**
 *
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 *
 */
public class TreeNode {

    private String key;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(String key) {
        this.key = key;
    }

    public TreeNode(String key, TreeNode left, TreeNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public TreeNode() {
    }

    public String getKey() {
        return key;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    /* checks if current TreeNode is Leaf*/
    public boolean isLeaf() {
        if (this.left == null && this.right == null) {
            return true;
        }
        return false;
    }

    public String toStringSuper() {
        return super.toString();
    }

    @Override
    public String toString() {
        return "Node{"
                + "key=" + key
                + ", left=" + left
                + ", right=" + right
                + '}';
    }
}
