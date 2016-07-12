package ueb21;

/**
 *
 * @author manuel
 */
public class TreeNode {

    private TreeNode left;
    private TreeNode right;
    private String key;

    public TreeNode(String key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return null;
    }
}
