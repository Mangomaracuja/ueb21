package ueb21;

import Exceptions.DListException;
import Exceptions.IdentifierException;
import Exceptions.IllegalOperationException;
import Exceptions.NoValueInHashTableException;
import Exceptions.StackException;

/**
 * Created by niklasreinhard on 18/07/16.
 */
public class test {
    public static void main(String[] args) throws DListException, IdentifierException, NoValueInHashTableException, StackException, IllegalOperationException {
        ExpressionTree expTree = new ExpressionTree();
        /*expTree.insertLeft(expTree.getRoot(), new TreeNode("Test2"));
        expTree.insertRight(expTree.getRoot(), new TreeNode("Test3"));
        expTree.printInOrder(expTree.getRoot());*/
        HashTabelle<String,Double> ht = new HashTabelle<>(10);
        
       
        
        ht.insert("manuel", 10.0);
        ht.insert("alex", 20.0);
        ht.insert("niklas", 30.0);
        ht.insert("guettler", 40.0);
        ht.insert("folz", 50.0);
        ht.insert("simon", 60.0);
        
        expTree.generateTree("( ( manuel * ( alex / niklas ) ) / ( guettler * ( folz - simon ) ) )", ht);
      
        System.out.print(expTree.toString());
        
        System.out.println(expTree.calculate());
        
    }
}

















