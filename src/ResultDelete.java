import java.util.ArrayList;
import java.util.Scanner;


public class ResultDelete {

    public static String red = "\u001B[31m", reset = "\u001B[0m";


    public void printResults(ArrayList<Result> resultater) {      //using in deleteResult
        for (int i = 0; i < resultater.size(); i++) {
            System.out.println(i + 1 + ": " + resultater.get(i));   //numerating results r
        }
        System.out.println(red + "Hvilket resultat vil du slette? (Tast 0 for tilbage til menu):" + reset);
    }


    public int readIndex(Scanner scanner, ArrayList<Result> resultater) {   //using in deleteResult
        while (true) {
            try {
                int index = Integer.parseInt(scanner.nextLine().trim()); //trim removes whitespace

                if (index == 0) return 0;

                if (index < 1 || index > resultater.size()) {
                    System.out.println("Resultat findes ikke. Prøv igen! (Tast 0 for menu)"); //OutOfBounds

                    continue;   //continue til valid or 0
                }
                return index;   //breaks the loop

            } catch (NumberFormatException e) { //lokalt
                System.out.println("Indtast et tal! (Tast 0 for menu) " + e.getMessage()); //Indtast et tal!
            }
        }
    }


    public boolean confirmDelete(Scanner scanner, Result r) {     //using in deleteResult
        System.out.println(red + "Tast \"bekræft\" for at fjerne resultat nummer for: " + r.getName() + reset);
        return (scanner.nextLine().trim().equalsIgnoreCase("bekræft"));
    }


    public void deleteResults(Scanner scanner, ArrayList<Result> resultater ) {
        if (resultater.isEmpty()) {
            System.out.println("Der er ingen resultater at slette!");
            return;
        }

        while (true) {
            printResults(resultater);

            try {
                int index = readIndex(scanner, resultater);

                if (index == 0) return;  //breaks loop

                Result r = resultater.get(index - 1);

                if (confirmDelete(scanner, r)) {
                    resultater.remove((index - 1));
                    System.out.println(red + "Resultat " + index + " for " + r.getName() +
                            " er nu fjernet fra listen!" + reset);

                } else {
                    System.out.println("Sletning af resultat er ikke gennemført!");
                }
                return;  //breaks loop either through if or else
            } catch (Exception e) {
                System.out.println("Nice try guys ;D");
            }
        }
    }

}