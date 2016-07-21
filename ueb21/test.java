package ueb21;


import ueb21.TreeNode;

/**
 * Created by niklasreinhard on 18/07/16.
 */
public class test {
    public static void main(String[] args) {
        ExpressionTree<String> expTree = new ExpressionTree<String>(new TreeNode("Test"));
        expTree.insertLeft(expTree.getRoot(), new TreeNode("Test2"));
        expTree.insertRight(expTree.getRoot(), new TreeNode("Test3"));

        expTree.printInOrder(expTree.getRoot());
    }
}
