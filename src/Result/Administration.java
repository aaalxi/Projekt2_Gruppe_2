package Result;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Administration {
    private final ArrayList<Result> resultater = new ArrayList<>();

    public ArrayList<Result> getResultater(){
        return resultater;
    }

    /**
     * Metode opretter et objekt Resultat
     * og tilføjer det til en arraylist Resultater.
     */
    public void addCompetitiveResult(Scanner scanner) {
        System.out.println("Stævne: ");
        String tournament = scanner.nextLine();

        System.out.println("Navn: ");
        String name = scanner.nextLine();

        System.out.println("Kategori (U18/O18): ");
        String category = scanner.nextLine().toUpperCase();

        System.out.println("Dato for stævne (yyyy-MM-dd):");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.println("Disciplin (BUTTERFLY/BACKSTROKE/BREASTSTROKE/FREESTYLE): ");
        String discipline = scanner.nextLine().toUpperCase();

        System.out.println("Distance (m): ");
        int distance = Integer.parseInt(scanner.nextLine());

        System.out.println("Tid (mm.ss): ");
        double time = Double.parseDouble(scanner.nextLine());

        System.out.println("Placering: ");
        int placement = Integer.parseInt(scanner.nextLine());

        CompetitionRes r = new CompetitionRes(tournament, name, category, date, discipline, distance, time, placement);
        resultater.add(r);
        System.out.println("Resultat tilføjet til kategorien: " + category + "!");
    }
//CompetitionRes (String tournament, String name, String category, LocalDate date, String discipline,
//                           int distance, double time, int placement)

    public void addTrainingResult(Scanner scanner) {
        System.out.println("Navn: ");
        String name = scanner.nextLine();

        System.out.println("Kategori (U18/O18): ");
        String category = scanner.nextLine().toUpperCase();

        System.out.println("Dato for stævne (yyyy-MM-dd):");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.println("Disciplin (BUTTERFLY/BACKSTROKE/BREASTSTROKE/FREESTYLE): ");
        String discipline = scanner.nextLine().toUpperCase();

        System.out.println("Distance (m): ");
        int distance = Integer.parseInt(scanner.nextLine());

        System.out.println("Tid (mm.ss): ");
        double time = Double.parseDouble(scanner.nextLine());

        TrainingRes r = new TrainingRes(name, category, date, discipline, distance, time);
        resultater.add(r);
        System.out.println("Resultat tilføjet til kategorien: " + category + "!");
    }


    public void printCompetitive() {
        ResultFilehandling.loadResult(resultater);
        for (Result r : resultater) {
            if (r instanceof CompetitionRes)
                System.out.println(r);
        }
    }


    public void printTraining() {
        ResultFilehandling.loadResult(resultater);
        for (Result r : resultater) {
            if (r instanceof TrainingRes) {
                System.out.println(r);
            }
        }
    }


    public ArrayList<Result> sortList(Scanner scanner, String category) {
        ArrayList<Result> filteredList = new ArrayList<>();

        System.out.println("Hvilken disciplin? (BUTTERFLY/BACKSTROKE/BREASTSTROKE/FREESTYLE): ");
        String disciplin = scanner.nextLine();

        System.out.println("Hvilken distance? (m):");
        int distance = scanner.nextInt();
        scanner.nextLine();

        for (Result r : resultater) {
            if (r.getCategory().equalsIgnoreCase(category)
                    && r.getDiscipline().equalsIgnoreCase(disciplin)
                    && r.getDistance().equals(distance)) {
                filteredList.add(r);
            }
        }
        filteredList.sort(new ResultComparitor());
        {
        }
        return filteredList;
    }


    public void topFive(Scanner scanner) {
        ArrayList<Result> listResults = new ArrayList<>();

        System.out.println("Hvilken kategori? (U18/O18): ");
        String category = scanner.nextLine().toUpperCase();

        listResults = sortList(scanner, category);

        for (int i = 0; i <= 4; i++) {
            Result r = listResults.get(i);
            System.out.println(r.toString());
        }
    }


    /**
     * Metode til at slette stævneResultater
     * går ind med et for-loop i resultater Arraylisten,
     * og leder efter index for et stævne resultat.
     * fjerner derefter objektet fra arraylisten.
     */
    public void deleteResults(Scanner scanner) {

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
        String valg = scanner.nextLine();

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