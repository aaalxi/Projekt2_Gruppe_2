import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class MemberAdministration {
    private static final Random gen = new Random();

    public static void createMember(MemberList memberList) {
        boolean loop = true;

        while (loop) {
            System.out.println(
                    "Er den nye medlem konkurrencesvømmer eller motionist?\n" +
                            "1. Konkurrencesvømmer\n" +
                            "2. Motionist\n" +
                            "0. Gå tilbage"
            );
            System.out.print("Valg: ");
            String valg = UI.scn.nextLine();

            switch (valg) {
                case "1" -> {
                    addSwimmer(true, memberList);
                    loop = false;
                }
                case "2" -> {
                    addSwimmer(false, memberList);
                    loop = false;
                }
                case "0" -> loop = false;
                default -> System.out.println("Ikke et valg, prøv igen.");
            }
        }
    }

    public static void addSwimmer(boolean isCompetitive, MemberList memberList) {
        System.out.println("Hvad er navnet?");
        String name = UI.scn.nextLine();

        String userID = createUserID(name);
        if (userID.isEmpty()) {
            System.out.println("Der blev ikke lavet et id til brugeren. Prøv igen.");
            return;
        }

        System.out.println("Hvad er fødselsdatoen? yyyy-mm-dd");
        LocalDate birthday = LocalDate.parse(UI.scn.nextLine());

        if (isCompetitive) {
            memberList.getAllMembers().add(new Competitive(userID, name, birthday, 0));
        } else {
            memberList.getAllMembers().add(new Casual(userID, name, birthday, 0));
        }

        memberList.addMembersToTeamList();
        System.out.println("Bruger lavet for " + name + ": " + birthday);
    }

    public static boolean disintegrateSwimmer(String id, MemberList memberList) {
        boolean removedFromAll = memberList.getAllMembers().removeIf(m -> m.getMemberID().equals(id));
        boolean removedFromUnder = memberList.getUnder18().removeIf(m -> m.getMemberID().equals(id));
        boolean removedFromOver = memberList.getOver18().removeIf(m -> m.getMemberID().equals(id));

        if (removedFromOver) {
            System.out.println("Fjernede " + id + " fra Over18 listen og fra AllMembers listen.");
        }
        if (removedFromUnder) {
            System.out.println("Fjernede " + id + " fra Under18 listen og fra AllMembers listen.");
        }
        if (removedFromAll && !removedFromOver && !removedFromUnder) {
            System.out.println("Fjernede " + id + " fra AllMembers listen.");
        }
        if (!removedFromOver && !removedFromUnder && !removedFromAll) {
            System.out.println(id + " er ikke et id i databasen.");
        }

        return removedFromAll || removedFromOver || removedFromUnder;
    }

    public static void addTrainer(MemberList memberList) {
        ArrayList<Member> valgtListe = null;

        System.out.print("Skriv trænerens navn: ");
        String newTrainer = UI.scn.nextLine();

        System.out.println("Vælg aldersgruppe træneren skal knyttes til:");
        System.out.println("1. Under 18");
        System.out.println("2. Over 18");
        System.out.println("0. Gå tilbage");
        System.out.print("Valg: ");

        while (true) {
            String valg = UI.scn.nextLine();
            switch (valg) {
                case "1" -> valgtListe = memberList.getUnder18();
                case "2" -> valgtListe = memberList.getOver18();
                case "0" -> { return; }
                default -> {
                    System.out.println("Prøv og skriv det igen");
                    continue;
                }
            }
            break;
        }

        for (Member m : valgtListe) {
            if (m instanceof Competitive c) {
                c.setTrainer(newTrainer);
            }
        }

        System.out.println(newTrainer + " er nu tilknyttet alle konkurrencesvømmere i denne aldersgruppe.");
    }

    public static void editMemberName(MemberList memberList) {
        boolean setName = true;

        System.out.print("Skriv medlems ID: ");
        String id = UI.scn.nextLine();

        for (Member m : memberList.getAllMembers()) {
            if (m.getMemberID().equals(id)) {
                System.out.print("Er du sikker på du vil ændre navnet på medlem :" + m.getName() + " [y/n] : ");
                String svar = UI.scn.nextLine();

                if (!svar.equalsIgnoreCase("y")) {
                    break;
                }

                while (setName) {
                    System.out.print("Skriv det ønskede navn: ");
                    String nytNavn = UI.scn.nextLine().trim();

                    if (InputValidering.isName(nytNavn)) {
                        System.out.println("Du har ændret " + m.getName() + " til " + nytNavn);
                        m.setName(nytNavn);
                        setName = false;
                    } else {
                        System.out.println("Ikke et gyldigt navn.");
                        System.out.print("Prøv igen: ");
                    }
                }
                break;
            }
        }
    }

    static String createUserID(String name) {
        int space = name.indexOf(" ");
        String firstTwo = name.substring(0, 2).toLowerCase();
        String lastTwo = (space == -1) ? "zz" : name.substring(space + 1, space + 3).toLowerCase();

        String newUserID = firstTwo + lastTwo
                + gen.nextInt(10) + gen.nextInt(10) + gen.nextInt(10) + gen.nextInt(10);

        if (InputValidering.validateUserID(newUserID)) {
            return newUserID;
        } else {
            System.out.println("Kunne ikke laver ID til brugeren.");
            return "";
        }
    }
}
