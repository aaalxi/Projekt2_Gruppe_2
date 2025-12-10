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


    public String confirmStringInput(Scanner scanner) {        //implementer 0=menu, sout, stævne/navn
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


    public LocalDate confirmDate(Scanner scanner) {
        while (true) {
            System.out.println("Indtast dato for resultatet (yyyy-MM-dd) (Tast 0 for menu):");
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


    public double confirmTime(Scanner scanner) {
        while (true) {
            System.out.println("Indtast \"back\" for menu):");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("back")) {
                return 0;
            }
            try {
                System.out.println("Indtast minutter (0-59): ");
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


    public int confirmPlacement(Scanner scanner) {
        while (true) {
            System.out.println("Indtast placering (Tast 0 for menu): ");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                continue;
            }
            if (InputValidering.confirmMumber(input)) {
                return Integer.parseInt(input);
            }
        }
    }

    public boolean confirmCompetitive(Scanner scanner) {
        boolean isCompetitive;
        System.out.println("Hvilket resultat? \n1. Træningsresultat \n2. Stævneresultat \n(Tast 0 for menu)");
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                isCompetitive = false;
                return isCompetitive;
            case "2":
                isCompetitive = true;
                return isCompetitive;
            case "0":
                break;
            default:
                System.out.println("Ugyldigt valg, prøv igen!");
        } return false;
    }


    public void addResult(Scanner scanner) {         //stævne, placer = comp
        while (true) {
            boolean isCompetitive = confirmCompetitive(scanner);

            System.out.println("Indtast navn på medlem (Tast 0 for menu):");
            String name = confirmStringInput(scanner);
            if (name == null) {
                break;
            }
            confirmCategory(scanner);
            String category = confirmCategory(scanner);
            if (category.isEmpty()) {
                break;
            }
            confirmDiscipline(scanner);
            Discipline discipline = confirmDiscipline(scanner);
            if (discipline == null) {
                break;
            }

            int distance = confirmDistance(scanner);
            if (distance == 0) {
                break;
            }

            LocalDate date = confirmDate(scanner);
            if (date == null) {
                break;
            }

            double time = confirmTime(scanner);
            if (time == 0) {
                break;
            }
            if (!isCompetitive) {
                ResultsTraining r = new ResultsTraining(name, category, date, discipline, distance, time);
                resultater.add(r);
                System.out.println("Resultat tilføjet til kategorien: " + category + "!");
            }

            if (isCompetitive) {
                System.out.println("Indtast navn på stævne (Tast 0 for menu):");
                String tournament = confirmStringInput(scanner);
                if (tournament == null) {
                    break;
                }
                int placement = confirmPlacement(scanner);
                ResultsCompetitive r = new ResultsCompetitive(tournament, name, category, date, discipline, distance, time, placement);
                resultater.add(r);
                System.out.println("Resultat tilføjet til kategorien: " + category + "!");

            }

        }
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


    public Discipline confirmDiscipline(Scanner scanner) {     //DONE using in topFive
        while (true) {
            System.out.print("Vælg disciplin: \n1. BUTTERFLY \n2. BACKSTROKE \n3. BREASTSTROKE \n4. FREESTYLE " +
                    "\n0. Tilbage til menu ");
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


    public int confirmDistance(Scanner scanner) {    //DONE using in printTop
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


    public String confirmCategory(Scanner scanner) {        //DONE Using in printTop
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
