package ueb21;

import Exceptions.IdentifierException;
import Exceptions.NoValueInHashTableException;
import Exceptions.StackException;
import java.util.NoSuchElementException;

/**
 * Created by niklasreinhard on 18/07/16.
 *
 * 
 */
public class ExpressionTree {

    private static final String MSG_UNKNOWN_IDENTIFIER = "Identifier Unbekannt!";

    private HashTabelle<String, Integer> table;
    private TreeNode root;

    public ExpressionTree() {
        this.root = null;
    }

    public TreeNode getRoot() {
        return this.root;
    }

    /* inserts new TreeNode 'left' as left child of node*/
 /* returns left TreeNode*/
    public TreeNode insertLeft(TreeNode node, TreeNode left) {
        if (this.root == null) {
            root = left;
        }
        {
            node.setLeft(left);
        }
        return left;
    }

    /* inserts new TreeNode 'right' as right child of node*/
 /* returns right TreeNode*/
    public TreeNode insertRight(TreeNode node, TreeNode right) {
        if (this.root == null) {
            root = right;
        } else {
            node.setRight(right);
        }
        return right;
    }

    public void insert(TreeNode node) {
        if (root == null) {
            root = node;
        } else {
            TreeNode current = root;
            TreeNode parent;

            while (true) {
                parent = current;
                String str = (String) node.getKey();
                if (str.compareTo((String) current.getKey()) < 0) {
                    current = current.getLeft();
                    if (current == null) {
                        parent.setLeft(node);
                        return;
                    }
                } else {
                    current = current.getRight();
                    if (current == null) {
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
     * @throws Exceptions.StackException
     */
    public void generateTree(String ausdruck, HashTabelle tb) throws NoValueInHashTableException, StackException, IdentifierException {
        this.table = tb;
        /* parse String into operands and operators*/
        String[] parse = ausdruck.split(" ");

        Stack<String> optStack = new Stack(String.class, 100);
        Stack<TreeNode> opndStack = new Stack(TreeNode.class, 100);
        TreeNode newNode;

        for (String s : parse) {
            if (!checkOperator(s)) {
                if (tb.get(s) == null) {
                    throw new IdentifierException(MSG_UNKNOWN_IDENTIFIER + s);
                }
                newNode = new TreeNode(s);
                opndStack.push(newNode);
            } else if (s.equals("(")) {
                optStack.push("(");
            } else if (s.equals(")")) {
                while (!optStack.empty()) {
                    String opTop = optStack.pop();
                    if (opTop.equals("(")) {
                        break;
                    } else {
                        newNode = new TreeNode(opTop);
                        newNode.setRight(opndStack.pop());
                        newNode.setLeft(opndStack.pop());
                        opndStack.push(newNode);
                    }
                }
            } else if (checkOperator(s)) {
                if (optStack.empty()) {
                    optStack.push(s);
                } else {
                    while (!optStack.empty()) {
                        String opTop = optStack.pop();
                        if (opTop.equals("(")) {
                            optStack.push(opTop);
                            break;
                        } else if (compareTo(opTop, s) < 0) {
                            optStack.push(opTop);
                            break;
                        } else {
                            newNode = new TreeNode(opTop);
                            newNode.setRight(opndStack.pop());
                            newNode.setLeft(opndStack.pop());
                            opndStack.push(newNode);
                        }
                    }
                    optStack.push(s);
                }
            }
        }

        while (!optStack.empty()) {
            String opTop = optStack.pop();
            TreeNode opTopKnoten = new TreeNode(opTop);
            opTopKnoten.setRight(opndStack.pop());
            opTopKnoten.setLeft(opndStack.pop());
            opndStack.push(opTopKnoten);
        }
        
        root = opndStack.pop();

        //            else if (optStack.empty()) {
        //                optStack.push(s);
        //            } else {
        //                while (!optStack.empty()) {
        //                    String opTop = optStack.pop();
        //                    if (opTop.equals("(")) {
        //                        optStack.push(s);
        //                    } else if (opTop.compareTo(s) < 0) { //to do schreibe eigene compare 
        //                        optStack.push(s);
        //                    } else {
        //                        TreeNode opTopKnoten = new TreeNode(s);
        //                        opTopKnoten.setRight(opndStack.pop());
        //                        opTopKnoten.setLeft(opndStack.pop());
        //                        opndStack.push(opTopKnoten);
        //                    }
        //                    if (opTop.equals("(") || opTop.compareTo(s) < 0) {
        //                        break;
        //                    }
        //                }
        //                optStack.push(s);
        //            }
    }

    private int zuweisenPrioritaet(String str) {
        int rechenzeichen[] = {1, 2, 3};
        int prio = 0;
        switch (str) {
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

    private int compareTo(String op1, String op2) {
        int op1rang = zuweisenPrioritaet(op1);
        int op2rang = zuweisenPrioritaet(op2);
        if (op1rang == op2rang) {
            return 0;
        } else if (op1rang > op2rang) {
            return 1;
        } else {
            return -1;
        }
    }

    /* Testen der checkOperand */
//    public void checkOperandTest(){
//        String str = "hallo + ( manuel * guettler )";
//        String[] parse = str.split(" ");
//        
//        for(String s: parse){
//            System.out.println(checkOperand(s));
//        }
//    }
    private boolean checkOperator(String s) {
        return (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*"));
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
    public double calculate() {
        return 0.0;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        TreeNode node = root;
        while (node.getLeft() != null) {
            node = node.getLeft();
            sb.append("|  ");
            i++;
        }
        sb.append("+--");
        sb.append(table.get(node.getKey()).toString());
        sb.append("\n");
        for(;i>0;i--) sb.append("|  ");
        
        
        return sb.toString();
    }
}
