package ueb21;

import Exceptions.DListException;
import Exceptions.IOException;
import Exceptions.IONotFoundException;
import Exceptions.IdentifierException;
import Exceptions.IllegalOperationException;
import Exceptions.NoValueInHashTableException;
import Exceptions.StackException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.LineNumberReader;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 */
public class Ueb21 {

    /*Variablen Deklaration*/
    private static final String MSG_NOT_FOUND = "Datei konnte nicht gefunden werden!";
    private static final String MSG_NO_FILE = "Ist keine einfache Datei!";
    private static final String MSG_NOT_READ = "Datei ist nicht lesbar";
    private static final String MSG_LINE_MISSING = "Erwartete Leerzeile fehlt!";
    private static final String DATEIEINGABE = "Name der Eingabedatei oder Return-Taste zum Beenden => ";
    private static final String RESULT = "Ergebnis: ";
    private static final String SYMBOLTABLE = "Symboltabelle:";
    private static final String BEFORECALC = "ExpressionTree bevor der Berechnung:";
    private static final String AFTERCALC = "ExpressionTree nach der Berechnung:";
    private static final String WITHOUTVALUES = "ExpressionTree ohne Werte:";
    private final ExpressionTree tree;
    private HashTabelle<String, Double> table;

    Scanner scanner;

    /**
     * Standartkonstruktor
     */
    private Ueb21() {
        this.tree = new ExpressionTree();
        scanner = new Scanner(System.in);
    }

    /**
     * Main-Methode ruft Start-Methode mit dem auszuführenden Code auf
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Ueb21().start();
    }

    /**
     * Start-Methode
     *
     */
    private void start() {
        try {
            File file = getFile();
            createTable(file);
            analyzeFile(file);
        } catch (IOException | java.io.IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    /**
     * Funktion zum Auswerten der übergebenen Datei.
     *
     * @param filename
     * @return file Rückgabewert der Datei
     * @throws DListIOException
     */
    private File getFile() {
        File file;
        System.out.print(DATEIEINGABE);
        while (scanner.hasNextLine()) {
            String filename = scanner.next();
            try {
                file = new File(filename);
                if (!file.exists()) {
                    throw new IONotFoundException(MSG_NOT_FOUND);
                } else if (!file.isFile()) {
                    throw new IONotFoundException(MSG_NO_FILE);
                } else if (!file.canRead()) {
                    throw new IOException(MSG_NOT_READ);
                }
                return file;
            } catch (IONotFoundException e) {
                System.out.print(e);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(1);
            }
        }
        System.exit(0);
        return null;
    }

    /**
     * Zählt die Zeilen in denen die Identifier Deklariert werden und erstell
     * dann eine Hashtabelle mit passender Länge.
     *
     * @param file
     */
    private void createTable(File file) throws FileNotFoundException,
            IOException, java.io.IOException {
        if (file == null) {
            throw new IOException(MSG_NO_FILE);
        }
        int size = 0;
        FileReader fr = new FileReader(file);
        LineNumberReader lnr = new LineNumberReader(fr);
        while (!lnr.readLine().isEmpty()) {
            size++;
        }
        this.table = new HashTabelle(size * 2);
    }

    /**
     * Funktion zum Auswerten von der Datei. Fängt Exceptions eventueller
     * Eingabefehler ab.
     *
     * @param file Aktuell übergebene Datei
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void analyzeFile(File file) throws FileNotFoundException,
            java.io.IOException, IOException {
        int linenr = 0;
        FileReader fr = new FileReader(file);
        LineNumberReader lnr = new LineNumberReader(fr);
        String line;

        try {
            do {
                line = lnr.readLine();
                line = line.trim();
                if (!line.isEmpty()) {
                    table.insertKey(line);
                }
                linenr++;
            } while (!line.isEmpty());

            line = lnr.readLine();
            linenr++;
            tree.generateTree(line, table);
            System.out.println(line + "\n");
            System.out.println(WITHOUTVALUES);
            display();
            if (!lnr.readLine().isEmpty()) {
                throw new IOException(MSG_LINE_MISSING + linenr);
            }

            while ((line = lnr.readLine()) != null) {
                analyzeLine(line);
            }
            analyzeLine("");
        } catch (IllegalOperationException | IdentifierException |
                DListException | NoValueInHashTableException |
                StackException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    /**
     * Werte Die einzelnen Zeilen aus, wenn Identifier deklariert werden, werden
     * sie in die Tabelle eingetragen, wenn der ausdruck steht wird dieser mit
     * der Tabelle an den Expressiontree übergeben, wenn wertzuweisung dann
     * werte in Tabelle eintragen. Fehlermeldung werfen wenn bei wertzuweisung
     * ein nicht deklarierter identifiert steht.
     *
     * @param zeile
     */
    private void analyzeLine(String zeile) throws DListException,
            IdentifierException, IllegalOperationException {
        if (zeile.isEmpty()) {
            System.out.println(SYMBOLTABLE);
            System.out.println(table.toString());
            System.out.println(BEFORECALC);
            display();
            System.out.println(RESULT + tree.calculate() + "\n");
            System.out.println(AFTERCALC);
            display();
            return;
        }

        String[] parse = zeile.split(" ");
        table.insertValue(parse[0], Double.parseDouble(parse[2]));
    }

    /**
     * Den Akuellen Baum Ausgeben.
     *
     */
    private void display() {
        System.out.println(tree.toString());
    }
}
