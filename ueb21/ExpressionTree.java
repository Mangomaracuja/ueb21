package ueb21;

import Exceptions.DListException;
import Exceptions.IdentifierException;
import Exceptions.IllegalOperationException;
import Exceptions.NoValueInHashTableException;
import Exceptions.StackException;
import java.util.NoSuchElementException;

/**
 * 
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 */
public class ExpressionTree {

    private static final String MSG_UNKNOWN_IDENTIFIER = "Identifier Unbekannt!";
    private static final String MSG_ILLEGAL_OPERATION = "Keine Gültige Rechenoperation!";

    private HashTabelle<String, Double> table;
    private TreeNode root;

    /**
     * StandardKonstruktor.
     *
     */
    public ExpressionTree() {
        this.root = null;
    }

    public TreeNode getRoot() {
        return this.root;
    }

    /**
     * Generiert einen Baum mithilfe der Hashtabelle.
     *
     * @param ausdruck
     * @param tb
     * @throws Exceptions.NoValueInHashTableException
     * @throws Exceptions.StackException
     * @throws Exceptions.IdentifierException
     * @throws Exceptions.DListException
     */
    public void generateTree(String ausdruck, HashTabelle tb) throws NoValueInHashTableException, StackException, IdentifierException, DListException {
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
                        if (table.get(opTop + newNode.toStringSuper()) == null) {
                            table.insertKey(opTop + newNode.toStringSuper());
                        }
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
                            if (table.get(opTop + newNode.toStringSuper()) == null) {
                                table.insertKey(opTop + newNode.toStringSuper());
                            }
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
            if (table.get(opTop + opTopKnoten.toStringSuper()) == null) {
                table.insertKey(opTop + opTopKnoten.toStringSuper());
            }
        }

        root = opndStack.pop();
    }

    /**
     * Methode zum zuweisen einer Priortät eines Operators.
     *
     * @param str
     * @return
     */
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

    /**
     * Vergleicht zwei Operatoren mit ihrer Wertigkeit und gibgt einen Integer
     * zurück. 0 bei identisch. -1 wenn op1 kleiner op2. +1 wenn op1 größer op2.
     *
     * @param op1
     * @param op2
     * @return
     */
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

    /**
     * Methode zum Überprüfen ob das übergebene String ein Operator ist.
     *
     * @param s
     * @return
     */
    private boolean checkOperator(String s) {
        return (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*") || s.equals("(") || s.equals(")"));
    }

    /**
     * Wertet den im Tree stehenden Ausdruck aus und gitb das Ergebnis zurück.
     *
     * @return
     * @throws Exceptions.IllegalOperationException
     * @throws Exceptions.IdentifierException
     */
    public double calculate() throws IllegalOperationException, IdentifierException {
        return calculateInOrder(root);
    }

    /**
     * Methode zum Berechnen des Trees inOrder rekursiv.
     *
     * @param localRoot
     * @return
     * @throws IllegalOperationException
     * @throws IdentifierException
     */
    private double calculateInOrder(TreeNode localRoot) throws IllegalOperationException, IdentifierException {
        if (localRoot != null && checkOperator(localRoot.getKey().substring(0, 1))) {
            double a = calculateInOrder(localRoot.getLeft());
            double b = calculateInOrder(localRoot.getRight());
            double erg = calc(a, b, localRoot);
            table.insertValue(localRoot.getKey() + localRoot.toStringSuper(), erg);
            return erg;
        }
        return table.getValue(localRoot.getKey());
    }

    /**
     * Methode zum unterscheiden der Verschiedenen Operatoren und berechet diese
     * dann.
     *
     * @param a
     * @param b
     * @param localRoot
     * @return
     * @throws IllegalOperationException
     */
    private double calc(double a, double b, TreeNode localRoot) throws IllegalOperationException {
        double erg = 0.0;
        switch (localRoot.getKey().charAt(0)) {
            case '+':
                erg = a + b;
                break;
            case '-':
                erg = a - b;
                break;
            case '*':
                erg = a * b;
                break;
            case '/':
                erg = a / b;
                break;
            default:
                throw new IllegalOperationException(MSG_ILLEGAL_OPERATION);
        }
        return erg;
    }

    /**
     * Formattiert ein Hashelement in ein String und gibt diesen zurück.
     *
     * @param he
     * @return
     */
    private String hashToString(HashElement<String, Double> he) {
        if (checkOperator(he.getKey().substring(0, 1))) {
            return String.format("{%s: :%.10s}", he.getKey().charAt(0), he.getWert());
        }
        return String.format("{%s: :%.10s}", he.getKey(), he.getWert());
    }

    /**
     * Formattiert den gesamtern Tree mithilfe eines StringBuilders, der
     * übergeben wird.
     *
     * @param localRoot
     * @param i
     * @param sb
     */
    private void toStringInOrder(TreeNode localRoot, int i, StringBuilder sb) {
        if (localRoot != null) {
            toStringInOrder(localRoot.getLeft(), i + 1, sb);
            for (int j = i; j > 0; j--) {
                sb.append("|  ");
            }
            sb.append("+--");
            if (checkOperator(localRoot.getKey())) {
                sb.append(hashToString(table.get(localRoot.getKey() + localRoot.toStringSuper())));
            } else {
                sb.append(hashToString(table.get(localRoot.getKey())));
            }
            sb.append("\n");
            toStringInOrder(localRoot.getRight(), i + 1, sb);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        toStringInOrder(root, 0, sb);

        return sb.toString();
    }
}
