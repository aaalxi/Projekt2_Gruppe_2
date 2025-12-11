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


    public String confirmStringInput(Scanner scanner) {        //using in addResult
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                return null;
            }
            if (InputValidering.isName(input)) {
                return input;
            }
            System.out.println("Ugyldigt valg, prøv igen!");
        }
    }


    public LocalDate confirmDate(Scanner scanner) {    //using in addResult
        while (true) {
            System.out.println("Indtast dato for resultatet (yyyy-MM-dd) (Tast 0 for menu):"); //format!
            System.out.print("Vælg: ");
            String dateInput = scanner.nextLine();

            if (dateInput.equals("0")) {
                return null;
            }
            try {
                return LocalDate.parse(dateInput);

            } catch (DateTimeParseException e) {
                System.out.print("Ugyldig dato, prøv igen! ");
            }
        }
    }


    public Double confirmTime(Scanner scanner) {   //using in addResult
        System.out.print("Indtast \"back\" for menu. Tryk enter eller andet for at fortsætte: ");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("back")) {
                return null;
            }
            try {
                System.out.print("Indtast minutter (0-59): ");
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
                return Double.parseDouble(tidStr);

            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input. Indtast kun hele tal.");
            }
        }
    }


    public Integer confirmPlacement(Scanner scanner) {   //using in addResult
        while (true) {
            System.out.println("Indtast placering (Tast 0 for menu): ");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                return null;
            }
            if (!InputValidering.confirmNumber(input)) {
                System.out.println("Ugyldigt valg, prøv igen!");
            }
            if (InputValidering.confirmNumber(input)) {
                return Integer.parseInt(input);
            }
        }
    }

    public Boolean confirmCompetitive(Scanner scanner) {   //using in addResult
        while (true) {
            System.out.println("Hvilket resultat vil du gemme? (Tast 0 for menu): " +
                    "\n1. Træningsresultat \n2. Stævneresultat");
            System.out.print("Vælg: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    return false;
                case "2":
                    return true;
                case "0":
                    return null;
                default:
                    System.out.println("Ugyldigt valg, prøv igen!");
            }
        }
    }

    public void addResult(Scanner scanner) {
        while (true) {
            System.out.println("---**********---");
            Boolean isCompetitive = confirmCompetitive(scanner);
            if (isCompetitive == null) {
                return;
            }
            System.out.println("---**********---");
            System.out.println("Indtast navn på medlem (Tast 0 for menu):");
            System.out.print("Vælg: ");
            String name = confirmStringInput(scanner);
            if (name == null) {
                break;
            }
            System.out.println("---**********---");
            String category = confirmCategory(scanner);
            if (category.isEmpty()) {
                break;
            }
            System.out.println("---**********---");
            Discipline discipline = confirmDiscipline(scanner);
            if (discipline == null) {
                break;
            }
            System.out.println("---**********---");
            int distance = confirmDistance(scanner);
            if (distance == 0) {
                break;
            }
            System.out.println("---**********---");
            LocalDate date = confirmDate(scanner);
            if (date == null) {
                break;
            }
            System.out.println("---**********---");
            Double time = confirmTime(scanner);
            if (time == null) {
                break;
            }
            if (!isCompetitive) {
                ResultsTraining r = new ResultsTraining(name, category, date, discipline, distance, time);
                resultater.add(r);
                System.out.println("Resultat tilføjet til kategorien: " + category + "!");
                break;
            }

            if (isCompetitive) {
                System.out.println("Indtast navn på stævne (Tast 0 for menu):");
                String tournament = confirmStringInput(scanner);
                if (tournament == null) {
                    break;
                }
                Integer placement = confirmPlacement(scanner);
                if (placement==null) {
                    break;
                }
                ResultsCompetitive r = new ResultsCompetitive(tournament, name, category, date, discipline, distance, time, placement);
                resultater.add(r);
                System.out.println("Resultat tilføjet til kategorien: " + category + "!");
                break;
            }
        }
    }


    public void printCompetitive() {
        for (Result r : resultater) {
            if (r instanceof ResultsCompetitive)
                System.out.println(r);
        }
    }

    public void printTraining() {
        for (Result r : resultater) {
            if (r instanceof ResultsTraining) {
                System.out.println(r);
            }
        }
    }


    public Discipline confirmDiscipline(Scanner scanner) {     //using in topFive
        while (true) {
            System.out.println("Vælg disciplin (Tast 0 for menu): \n1. BUTTERFLY \n2. BACKSTROKE " +
                    "\n3. BREASTSTROKE \n4. FREESTYLE");
            System.out.print("Vælg: ");
            String input = scanner.nextLine().trim();

            try {
                switch (input) {
                    case "1":
                        return Discipline.BUTTERFLY;
                    case "2":
                        return Discipline.BACKSTROKE;
                    case "3":
                        return Discipline.BREASTSTROKE;
                    case "4":
                        return Discipline.FREESTYLE;
                    case "0":
                        return null;
                    default:
                        System.out.println("Ugyldigt valg! Prøv igen");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig disciplin, prøv igen!");
            }
        }
    }


    public int confirmDistance(Scanner scanner) {    //using in printTop
        while (true) {
            System.out.println("Vælg en distance i disciplinen (m) (Tast 0 for menu): \n1. 100 m \n2. 200 m \n3. 300 m");
            System.out.print("Vælg: ");
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


    public String confirmCategory(Scanner scanner) {        //using in sortedlist and addResult
        while (true) {
            System.out.println("Vælg en katogori (Tast 0 for menu): \n1. U18 \n2. O18");
            System.out.print("Vælg: ");
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

    public ArrayList<Result> sortedList(Scanner scanner) { //using in printTop
        ArrayList<Result> filteredList = new ArrayList<>();

        while (true) {
            try {
                String category = confirmCategory(scanner);
                if (category.isEmpty()) {
                    break;
                }
                Discipline discipline = confirmDiscipline(scanner);
                if (discipline == null) {
                    break;
                }

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
        }
        return filteredList;
    }


    public void printTop(Scanner scanner) {    //prints top 5 at least
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
