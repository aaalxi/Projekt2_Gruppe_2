import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidering {

    static boolean validateUserID(String id) {
        return id.matches("^[a-z]{4}[0-9]{4}$");
    }


    /**
     * Denne metode tjekker om en String er et gyldigt navn.
     * Ved at se om det indeholder a-z stor og småt samt æøå - og mellemrum og min 1 tegn.
     *
     * @param s
     * @return true hvis ikke s er tom og indeholder kravne.
     */
    public static boolean isName(String s) {
        return s != null && s.matches("[a-zA-ZæøåÆØÅ\\- ]+"); //returns true, if condition meet
    }


    public static LocalDate localDateCheck(String s) {
        try {
            return LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            System.out.println("Dato ikke skrevet korrekt. Prøv Igen");
        }
        return null;
    }


    public static boolean confirmNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static double doubleInRange(Scanner scanner, double maxValue) {
        double value;

        while (true) {
            if (!scanner.hasNextDouble()) {
                System.out.println("Indtast et gyldigt beløb!");
                scanner.nextLine();
                continue;       //forsætter loop, men springer resten i iterationen over (tomt)
            }
            value = scanner.nextDouble();
            scanner.nextLine();

            if (value < 0 || value > maxValue) {
                System.out.println("Beløbet skal være mellem 0 og " + maxValue);
                continue;
            }
            return value;
        }
    }
}