
package Result;

import java.util.ArrayList;
import java.util.Scanner;

public class Administration {
    private ArrayList<Result> resultater = new ArrayList<>();
    private Scanner scn = new Scanner(System.in);

    /**
     * Metode opretter et objekt Resultat
     * og tilføjer det til en arraylist Resultater.
     */
    public void addResult() {
        System.out.println("Konkurrence eller træningsresultater: ");
        int choose = scn.nextInt();

        System.out.println("Stævne: ");
        String tournament = scn.nextLine();

        System.out.println("Navn: ");
        String name = scn.nextLine();

        System.out.println("Kategori: ");
        String category = scn.nextLine();

        System.out.println("Disciplin: ");
        String discipline = scn.nextLine();

        System.out.println("Distance: ");       //bestemmer selv
        int distance = Integer.parseInt(scn.nextLine());

        System.out.println("Tid: ");
        double time = Double.parseDouble(scn.nextLine());

        System.out.println("Placering: ");
        int placement = Integer.parseInt(scn.nextLine());

        CompetitionRes r = new CompetitionRes(tournament, name, category, discipline, distance, time, placement);
        resultater.add(r);

        System.out.println("Resultat tilføjet til kategorien: " + category + "!");
    }

    /**
     * Metode til at slette stævneResultater
     * går ind med et for-loop i resultater Arraylisten,
     * og leder efter index for et stævne resultat.
     * fjerner derefter objektet fra arraylisten.
     */
    public void deleteResults() {

        if (resultater.isEmpty()) {
            System.out.println("Der er ingen resultater at slette!");
            return;
        }

        System.out.println("Hvilket resultat?");
        for (int i = 0; i < resultater.size(); i++) {
            Result r = resultater.get(i);

            if (r instanceof CompetitionRes) {
            //tostring
            }
        }


        System.out.println("Indtast nummeret på resultatet");
        String valg = scn.nextLine();

        try {
            int index = Integer.parseInt(valg);

            if (index < 0) {
                System.out.println("Ugyldigt nummer, prøv igen");
                return;
            }
            if (index > resultater.size()) {
                System.out.println("Ugyldigt nummer, prøv igen");
                return;
            }

            Result removed = resultater.remove(index);
          //  System.out.println("Resultat fjernet for " + removed.name);


        } catch (NumberFormatException e) {
            System.out.println("Indtast et tal");
        }
    }
}