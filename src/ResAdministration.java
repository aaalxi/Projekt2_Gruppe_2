import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ResAdministration {
    private final ArrayList<Result> resultater = new ArrayList<>();

    public ArrayList<Result> getResultater() {
        return resultater;
    }


    public void addCompetitiveResult(Scanner scanner) {
        String category, valg;
        System.out.println("Stævne: ");
        String tournament = scanner.nextLine();

        System.out.println("Navn: ");
        String name = scanner.nextLine();
        if (!InputValidering.isName(name)) {
            System.out.println("Ikke et gyldigt navn.");
            return;
        }

        System.out.println("Vælg en katogori\n" +
                "1. U18\n" +
                "2. O18");
        System.out.print("Valg: ");
        valg = scanner.nextLine();
        switch (valg) {
            case "1" -> category = "U18";
            case "2" -> category = "O18";
            default -> {
                System.out.println("Ikke et gyldigt valg.");
                return;
            }
        }

        System.out.println("Dato for stævne (yyyy-MM-dd):");
        LocalDate date;
        try {
            date = LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.print("Ugyldig dato: ");
            return;
        }

        System.out.print("Vælg disciplin (BUTTERFLY/BACKSTROKE/BREASTSTROKE/FREESTYLE): ");
        String d = scanner.nextLine().toUpperCase();
        try {
            Discipline discipline = Discipline.valueOf(d);
            d = discipline.toString();

        } catch (IllegalArgumentException e) {
            System.out.println("Ugyldig disciplin.");
            return;
        }

        System.out.println("Distance (m)\n" +
                "1. 100 m\n" +
                "2. 200 m\n" +
                "3. 300 m");
        int distance = 0;
        valg = scanner.nextLine();
        switch (valg) {
            case "1" -> distance = 100;
            case "2" -> distance = 200;
            case "3" -> distance = 300;
            default -> System.out.println("Ikke et gyldigt valg");
        }

        System.out.println("Tid (mm.ss): ");
        double time;

        while (true) {
            try {
                System.out.println("Minutter: ");
                int minutter = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Sekunder (0-59): ");
                int sekunder = Integer.parseInt(scanner.nextLine().trim());

                if (minutter < 0) {
                    System.out.println("Minutter kan ikke være negative.");
                    continue;
                }
                if (sekunder < 0 || sekunder > 59) {
                    System.out.println("Sekunder skal være mellem 0 og 59.");
                    continue;
                }

                String tidStr = minutter + "." + String.format("%02d", sekunder);
                time = Double.parseDouble(tidStr);

                break;
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input. Indtast kun hele tal.");
            }
        }

        System.out.println("Placering: ");
        String input = scanner.nextLine();

        int placement;
        try {
            placement = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Ugyldigt input.");
            return;
        }

        CompetitionRes r = new CompetitionRes(tournament, name, category, date, d, distance, time, placement);
        resultater.add(r);
        System.out.println("Resultat tilføjet til kategorien: " + category + "!");
    }


    public void addTrainingResult(Scanner scanner) {
        String valg;
        String category;
        System.out.println("Navn: ");
        String name = scanner.nextLine();
        if (!InputValidering.isName(name)) {
            System.out.println("Ikke et gyldigt navn.");
            return;
        }
        System.out.println("Vælg en katogori\n" +
                "1. U18\n" +
                "2. O18");
        valg = scanner.nextLine();
        switch (valg) {
            case "1" -> category = "U18";
            case "2" -> category = "O18";
            default -> {
                System.out.println("Ikke et gyldigt valg.");
                return;
            }
        }

        System.out.println("Dato for stævne (yyyy-MM-dd):");
        LocalDate date;
        try {
            date = LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.print("Ugyldig dato: ");
            return;
        }

        System.out.print("Vælg disciplin (BUTTERFLY/BACKSTROKE/BREASTSTROKE/FREESTYLE): ");
        String d = scanner.nextLine().toUpperCase();

        try {
           Discipline discipline = Discipline.valueOf(d);
           d = discipline.toString();

        } catch (IllegalArgumentException e) {
            System.out.println("Ugyldig disciplin.");
            return;
        }

        System.out.println("Distance (m)\n" +
                "1. 100 m\n" +
                "2. 200 m\n" +
                "3. 300 m");
        int distance = 0;
        valg = scanner.nextLine();
        switch(valg){
            case "1" -> distance = 100;
            case "2" -> distance = 200;
            case "3" -> distance = 300;
            default -> System.out.println("Ikke et gyldigt valg");
        }

        System.out.println("Tid (mm.ss): ");
        double time;

        while(true){
            try{
                System.out.println("Minutter: ");
                int minutter = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Sekunder (0-59): ");
                int sekunder = Integer.parseInt(scanner.nextLine().trim());

                if (minutter < 0) {
                    System.out.println("Minutter kan ikke være negative.");
                    continue;
                }
                if (sekunder < 0 || sekunder > 59) {
                    System.out.println("Sekunder skal være mellem 0 og 59.");
                    continue;
                }

                String tidStr = minutter + "." + String.format("%02d", sekunder);
                time = Double.parseDouble(tidStr);

                break;
            }catch (NumberFormatException e){
                System.out.println("Ugyldigt input. Indtast kun hele tal.");
            }
        }
        TrainingRes r = new TrainingRes(name, category, date, d, distance, time);
        resultater.add(r);
        System.out.println("Resultat tilføjet til kategorien: " + category + "!");
    }

    //Hvis fucked tilføj load metode igen
    public void printCompetitive() {
        for (Result r : resultater) {
            if (r instanceof CompetitionRes)
                System.out.println(r);
        }
    }

    //Hvis fucked tilføj load metode igen
    public void printTraining() {
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
        return filteredList;
    }


    public void topFive(Scanner scanner) {
        ArrayList<Result> listResults;

        System.out.println("Hvilken kategori? (U18/O18): ");
        String category = scanner.nextLine().toUpperCase();

        listResults = sortList(scanner, category);

        if(listResults.size() >= 5){
            for (int i = 0; i <= 4; i++) {
                Result r = listResults.get(i);
                System.out.println(r.toString());
            }
        }
        else{
            for (int i = 0; i < listResults.size(); i++){
                Result r = listResults.get(i);
                System.out.println(r.toString());
            }
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

        System.out.println("Hvilket resultat vil du slette?");
        for (int i = 0; i < resultater.size(); i++) {
            Result r = resultater.get(i);
            System.out.print(i+1 +": \t");
            System.out.println(r);
        }
         //   if (r instanceof CompetitionRes) {
                //System.out.println(r);
            }
        }
/*

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
    }*/
//}