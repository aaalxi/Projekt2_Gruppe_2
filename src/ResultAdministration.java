import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ResultAdministration {
    private final ArrayList<Result> resultater = new ArrayList<>();
    public static String red = "\u001B[31m", reset = "\u001B[0m";

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

      //  ResultsCompetitive r = new ResultsCompetitive(tournament, name, category, date, discipline, distance, time, placement);
       // resultater.add(r);
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
            System.out.println("Ugyldig disciplin!");
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
       // ResultsTraining r = new ResultsTraining(name, category, date, d, distance, time);
        //resultater.add(r);
        System.out.println("Resultat tilføjet til kategorien: " + category + "!");
    }

    //Hvis fucked tilføj load metode igen
    public void printCompetitive() {
        for (Result r : resultater) {
            if (r instanceof ResultsCompetitive)
                System.out.println(r);
        }
    }

    //Hvis fucked tilføj load metode igen
    public void printTraining() {
        for (Result r : resultater) {
            if (r instanceof ResultsTraining) {
                System.out.println(r);
            }
        }
    }


    public Discipline confirmDiscipline(Scanner scanner) {     //using in topFive
        while (true) {
            System.out.print("Vælg disciplin: \n1. BUTTERFLY \n2. BACKSTROKE \n3. BREASTSTROKE/FREESTYLE) " +
                    "\n0. Tilbage til menu): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("0")) {
                break;
            }
            try {
                return Discipline.valueOf(input);

            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig disciplin, prøv igen!");
            }
        } return null;
    }


    public int confirmDistance(Scanner scanner) {    //done
        while (true) {
            System.out.println("Vælg en distance i disciplinen (m): \n1. 100 m \n2. 200 m \n3. 300 m \n0. Tilbage til menu");
            String valg = scanner.nextLine().trim();

            switch (valg) {
                case "1":
                    return 100;
                case "2":
                    return 200;
                case "3":
                    return 300;
                case "0":
                    return 0;
                default:
                    System.out.println("Ikke et gyldigt valg, prøv igen!");
            }
        }
    }


    public String confirmCategory(Scanner scanner) {        //DONE Using in topFive
        while (true) {
            System.out.println("Vælg en katogori \n1. U18 \n2. O18 \n0. Tilbage til menu");
            String valg = scanner.nextLine().trim();

            switch (valg) {
                case "1":
                    return "U18";
                case "2":
                    return "O18";
                case "0":
                    return "";
                default:
                    System.out.println("Ugyldigt valg! Prøv igen");
            }
        }
    }

    public ArrayList<Result> sortedList(Scanner scanner) { //using in topFive
        ArrayList<Result> filteredList = new ArrayList<>();

        while (true) {
            try {
                String category = confirmCategory(scanner);
                if (category.isEmpty()) {
                    break;
                }
                Discipline discipline = confirmDiscipline(scanner);

                int distance = confirmDistance(scanner);
                if (distance == 0) {
                    break;
                }
                for (Result r : resultater) {
                    if (r.getCategory().equalsIgnoreCase(category)
                            && r.getDiscipline().equals(discipline.toString())
                            && r.getDistance() == distance) {
                        filteredList.add(r);
                    }
                }
                filteredList.sort(new ResultComparitor());
                return filteredList;

            } catch (Exception e) {
                System.out.println("Ikke et gyldigt valg!");
            }
        } return filteredList;
    }


    public void printTop(Scanner scanner) {  //DONE
        ArrayList<Result> listResults;
        listResults = sortedList(scanner);

        if (listResults.isEmpty()) {
            return;
        }

        if (listResults.size() >= 5) {
            for (int i = 0; i <= 4; i++) {
                Result r = listResults.get(i);
                System.out.println(r.toString());
            }
        } else {
            for (Result r : listResults) {
                System.out.println(r.toString());
            }
        }
    }

}
