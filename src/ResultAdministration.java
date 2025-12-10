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

        ResultsCompetitive r = new ResultsCompetitive(tournament, name, category, date, d, distance, time, placement);
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
        ResultsTraining r = new ResultsTraining(name, category, date, d, distance, time);
        resultater.add(r);
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
            System.out.print("Vælg disciplin (BUTTERFLY/BACKSTROKE/BREASTSTROKE/FREESTYLE): ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Discipline.valueOf(input);

            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig disciplin, prøv igen!");
            }
        }
    }


    public int confirmDistance(Scanner scanner) {
        int distance = 0;
        while (true) {
            System.out.println("Distance (m) \n1. 100 m \n2. 200 m \n3. 300 m");
            String valg = scanner.nextLine().trim();

            switch (valg) {
                case "1":
                    distance = 100;
                    break;
                case "2":
                    distance = 200;
                    break;
                case "3":
                    distance = 300;
                    break;
                default:
                    System.out.println("Ikke et gyldigt valg");
                    continue;
            }
            return distance;
        }
    }


    public String confirmCategory(Scanner scanner) {
        while (true) {
            System.out.println("Vælg en katogori \n1. U18 \n2. O18");
            try {
                String valg = scanner.nextLine().trim();
                switch (valg) {
                    case "1": return "U18";
                    case "2": return "O18";
                    default: throw new IllegalStateException("Unexpected value: " + valg);
                }
            } catch (Exception e) {
                System.out.println("Prøv igen!");
            }
        }
    }

    public ArrayList<Result> sortedList(Scanner scanner) {
        ArrayList<Result> filteredList = new ArrayList<>();

        try {
            String category = confirmCategory(scanner);
            Discipline discipline = confirmDiscipline(scanner);
            int distance = confirmDistance(scanner);

            for (Result r : resultater) {
                if (r.getCategory().equalsIgnoreCase(category)
                        && r.getDiscipline().equals(discipline.name())
                        && r.getDistance() == distance) {
                    filteredList.add(r);
                }
            }
            filteredList.sort(new ResultComparitor());
            return filteredList;
        } catch (Exception e) {
            System.out.println("FEJL");
            return new ArrayList<>();
        }
}

    /*System.out.print("Vælg disciplin (BUTTERFLY/BACKSTROKE/BREASTSTROKE/FREESTYLE): ");
        String d = scanner.nextLine().toUpperCase();

        try {
            Discipline discipline = Discipline.valueOf(d);
            d = discipline.toString();

        } catch (IllegalArgumentException e) {
            System.out.println("Ugyldig disciplin!");
            return;
        }*/


public void topFive(Scanner scanner) {
    ArrayList<Result> listResults;

    listResults = sortedList(scanner);

    if (listResults.size() >= 5) {
        for (int i = 0; i <= 4; i++) {
            Result r = listResults.get(i);
            System.out.println(r.toString());
        }
    } else {
        for (int i = 0; i < listResults.size(); i++) {
            Result r = listResults.get(i);
            System.out.println(r.toString());
        }
    }
}


public void printResults() {      //using in deleteResult
    for (int i = 0; i < resultater.size(); i++) {
        System.out.println(i + 1 + ": " + resultater.get(i));   //numerating results r
    }
    System.out.println(red + "Hvilket resultat vil du slette? (Tast 0 for tilbage til menu):" + reset);
}


public int readIndex(Scanner scanner) {   //using in deleteResult
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


public void deleteResults(Scanner scanner) {
    if (resultater.isEmpty()) {
        System.out.println("Der er ingen resultater at slette!");
        return;
    }

    while (true) {
        printResults();

        try {
            int index = readIndex(scanner);

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
