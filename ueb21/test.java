package ueb21;

import Exceptions.DListException;
import Exceptions.IdentifierException;
import Exceptions.NoValueInHashTableException;

/**
 * Created by niklasreinhard on 18/07/16.
 */
public class test {
    public static void main(String[] args) throws DListException, IdentifierException, NoValueInHashTableException {
        ExpressionTree<String> expTree = new ExpressionTree<>();
        /*expTree.insertLeft(expTree.getRoot(), new TreeNode("Test2"));
        expTree.insertRight(expTree.getRoot(), new TreeNode("Test3"));
        expTree.printInOrder(expTree.getRoot());*/
        HashTabelle<String,Integer> ht = new HashTabelle<>(10);
        
       
        
        ht.insert("hallo", 10);
        ht.insert("ficken", 20);
        
        expTree.generateTree("hallo + ficken", ht);
      
        expTree.printInOrder(expTree.getRoot());
    }
}