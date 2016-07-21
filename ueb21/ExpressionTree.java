package ueb21;

import Exceptions.NoValueInHashTableException;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by niklasreinhard on 18/07/16.
 * @param <T>
 */
public class ExpressionTree<T> {
    
    private HashTabelle<String, Integer> table;

    /*

    Sample usage:

     ExpressionTree<String> expTree = new ExpressionTree<String>(new TreeNode("Test"));
        expTree.insertLeft(expTree.getRoot(), new TreeNode("Test2"));
        expTree.insertRight(expTree.getRoot(), new TreeNode("Test3"));
        expTree.printInOrder(expTree.getRoot());

     */
    
    private TreeNode<T> root;

    public ExpressionTree(TreeNode<T> root) {
        this.table = new HashTabelle(10);
        this.root = root;
    }

    public ExpressionTree() {
    }
    
    

    public TreeNode<T> getRoot(){
        return this.root;
    }
    /* inserts new TreeNode 'left' as left child of node*/
    /* returns left TreeNode*/
    public TreeNode<T> insertLeft(TreeNode<T> node, TreeNode<T> left){
        if(this.root==null){
            root = left;
        }{
            node.setLeft(left);
        }
        return left;
    }
    /* inserts new TreeNode 'right' as right child of node*/
    /* returns right TreeNode*/
    public TreeNode<T> insertRight(TreeNode<T> node, TreeNode<T> right){
        if(this.root==null){
           root = right;
        }else{
        node.setRight(right);
        }
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
    
    public void insert(TreeNode<T> node){
        if(root==null){
            root = node;
        }else{
            TreeNode current = root;
            TreeNode parent;
            
            while(true){
                parent = current;
                String str = (String) node.getKey();
                if(str.compareTo((String)current.getKey())<0){
                    current = current.getLeft();
                    if(current == null){
                        parent.setLeft(node);
                        return;
                    }
                }else{
                    current = current.getRight();
                    if(current == null){
                        parent.setRight(node);
                        return;
                    }
                }
                
            }
        }
    }
    /**
     * Generiert einen Baum mithilfe der Hashtabelle
     * 
     * @param ausdruck
     * @param tb 
     * @throws Exceptions.NoValueInHashTableException 
     */
    public void generateTree(String ausdruck, HashTabelle tb) throws NoValueInHashTableException {
        this.table = tb;
        /* parse String into operands and operators*/
        String[] parse = ausdruck.split(" ");
        
          
            
        Stack<String> optStack = new Stack();
        Stack<TreeNode> opndStack = new Stack();
        
        
        for(String s: parse){
            if(checkOperand(s)){
                TreeNode newNode = new TreeNode(s);
                insert(newNode);
                opndStack.push(newNode);
            }else{
                if(optStack.empty()){
                    optStack.push(s);
                }else{
                    while(!optStack.empty()){
                        String opTop = optStack.pop();
                        if(opTop.equals("(")){
                            optStack.push(s);
                        }else if(opTop.compareTo(s)<0){ //to do schreibe eigene compare 
                            optStack.push(s);
                        }else{
                            TreeNode opTopKnoten = new TreeNode(s);
                            opTopKnoten.setRight(opndStack.pop());
                            opTopKnoten.setLeft(opndStack.pop());
                            opndStack.push(opTopKnoten);
                        }
                        if(opTop.equals("(") || opTop.compareTo(s)<0){
                            break;
                        }
                    }
                    optStack.push(s);
                }
            }
        }
        
        while(!optStack.empty()){
            String opTop = optStack.pop();
            TreeNode opTopKnoten = new TreeNode(opTop);
            opTopKnoten.setRight(opndStack.pop());
            opTopKnoten.setLeft(opndStack.pop());
            opndStack.push(opTopKnoten);
        }
            
        
        
    }
    
     private int zuweisenPrioritaet(String str){
        int rechenzeichen[] = {1,2,3};
        int prio = 0;
        switch(str){
            case "*":
                prio = rechenzeichen[1];
                break;
            case "/": 
                prio = rechenzeichen[1];
                break;
            case "+":
                prio = rechenzeichen[0];
                break;
            case "-":
                prio = rechenzeichen[0];
                break;
            case "(":
                prio = rechenzeichen[2];
                break;
            case ")":
                prio = rechenzeichen[2];
                break;
            default:
                throw new NoSuchElementException();
        }
        return prio;
    }
    
    private int compareTo(String op1, String op2){
        int op1rang = zuweisenPrioritaet(op1);
        int op2rang = zuweisenPrioritaet(op2);
        if(op1rang==op2rang){
            return 0;
        }else if(op1rang>op2rang){
            return 1;
        }else{
            return 2;
        }
    }
    
    private boolean checkOperand(String s){
       if(s.equals("+") || s.equals("-")){
           return false;//kein Operand
       }
       return true;
    }
    
//    private void checkOperands(String[] operands) throws NoValueInHashTableException{
//        /* iterate through operands array and check if operator or operand*/
//        for (String s : operands) {
//            /* if parse[i] is no operator, check if corresponding value is stored in hashtable*/
//            if (!checkOperator(s)) {
//                if (table.getValue(s) == null) {
//                    throw new NoValueInHashTableException("Kein zugehöriger Wert in HashTabelle gespeichert");   
//                }
//            }
//        }
//    }
    
  
    
    /**
     * Wertet den im Tree stehenden Ausdruck aus und gitb das Ergebnis zurück.
     * 
     * @return 
     */
    public double calculate(){
        return 0.0;
    }
}
