package ueb21;

import Exceptions.DListException;
import Exceptions.IONotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.LineNumberReader;
import java.io.File;
import java.util.regex.*;

/**
 *
 * @author Manuel Jung; Alexander Stolz; Niklas Reinhard;
 */
public class Ueb21 {

    /*Variablen Deklaration*/
    private static final String MSG_NO_ARGUMENTS = "Keine Argumente übergeben!";
    private static final String MSG_UNKNOWN_EXCEPTION = "Ein unerwarteter Fehler ist aufgetreten:\n%s\n";
    private static final String MSG_UNKNOWN_IOEXCEPTION = "Ein unerwarteter IOFehler ist aufgetreten:\n%s\n";
    private static final String MSG_NOT_FOUND = "Datei konnte nicht gefunden werden!";
    private static final String MSG_NAME_NOT_FOUND = "Element konnte nicht gefunden werden";
    private static final String MSG_NO_FILE = "Ist keine einfache Datei!";
    private static final String MSG_NOT_READ = "Datei ist nicht lesbar";
    private static final String EQUALS = " = ";
    private static final String regex = "(.+?)\\s=\\s(.+?)";

    private ExpressionTree tree;
    private HashTabelle<String, Integer> table;

    /**
     * Standartkonstruktor
     */
    private Ueb21() {
        this.tree = new ExpressionTree();
    }

    /**
     * Main-Methode ruft Start-Methode mit dem auszuführenden Code auf
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println(MSG_NO_ARGUMENTS);
            System.exit(1);
        }
        new Ueb21().start(args);
    }

    /**
     * Start-Methode
     *
     */
    private void start(String[] args) {
        try {
            File file = getFile(args[0]);
            analyzeFile(file);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        } catch (Exception e) {
            System.out.printf(MSG_UNKNOWN_EXCEPTION, e);
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
    private File getFile(String filename) throws IOException, IONotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new IONotFoundException(MSG_NOT_FOUND);
        } else if (!file.isFile()) {
            throw new IONotFoundException(MSG_NO_FILE);
        } else if (!file.canRead()) {
            throw new IOException(MSG_NOT_READ);
        }
        return file;
    }

    /**
     * Funktion zum Auswerten von der Datei. Fängt Exceptions eventueller
     * Eingabefehler ab.
     *
     * @param file Aktuell übergebene Datei
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void analyzeFile(File file) throws FileNotFoundException, IOException {
        int size = 0;
        FileReader fr = new FileReader(file);
        LineNumberReader lnr = new LineNumberReader(fr);
        String line;
        while ((line = lnr.readLine()) != null) {
            System.out.println(line);
            try {
                line = line.trim();
                analyzeLine(line);
                size++;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        this.table = new HashTabelle(size);
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
    private void analyzeLine(String zeile) throws DListException {
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(zeile);
        if (zeile.contains(EQUALS)) {
            String name = m.group(1);
            String key = m.group(2);
            int wert = Integer.valueOf(key);
            table.insert(name, wert);
        }
    }

    /**
     * Den Akuellen Baum Ausgeben.
     *
     */
    private void display() {
        tree.toString();
    }
}
