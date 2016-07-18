/**
 * Created by niklasreinhard on 18/07/16.
 */
public class test {
    public static void main(String[] args) {
        ExpressionTree<String> expTree = new ExpressionTree<String>(new Node("Test"));
        expTree.insertLeft(expTree.getRoot(), new Node("Test2"));
        expTree.insertRight(expTree.getRoot(), new Node("Test3"));

        expTree.printInOrder(expTree.getRoot());
    }
}
