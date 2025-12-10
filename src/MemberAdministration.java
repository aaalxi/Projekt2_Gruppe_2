import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MemberAdministration {
    private static final Random gen = new Random();

    public static void createMember(MemberList memberList, Scanner scn) {
        boolean loop = true;

        while (loop) {
            System.out.println(
                    "Er den nye medlem konkurrencesvømmer eller motionist?\n" +
                            "1. Konkurrencesvømmer\n" +
                            "2. Motionist\n" +
                            "0. Gå tilbage"
            );
            System.out.print("Valg: ");
            String valg = scn.nextLine();

            switch (valg) {
                case "1" -> {
                    addSwimmer(true, memberList,scn);
                    loop = false;
                }
                case "2" -> {
                    addSwimmer(false, memberList, scn);
                    loop = false;
                }
                case "0" -> loop = false;
                default -> System.out.println("Ikke et valg, prøv igen.");
            }
        }
    }

    public static void addSwimmer(boolean isCompetitive, MemberList memberList, Scanner scn) {
        System.out.println("Hvad er navnet?");
        String name = scn.nextLine();
        if (!InputValidering.isName(name)){
            System.out.println("Ugyldige tegn brugt. Prøv igen.");
            return;
        }

        String userID = createUserID(name);
        if (userID.isEmpty()) {
            System.out.println("Der blev ikke lavet et id til brugeren. Prøv igen.");
            return;
        }

        LocalDate birthday;
        while (true) {
            System.out.println("Hvad er fødselsdatoen? yyyy-mm-dd");
            LocalDate date = InputValidering.localDateCheck(scn.nextLine());
            if(date != null){
                birthday = date;
                break;
            }
        }


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

    public static void addTrainer(MemberList memberList, Scanner scn) {
        ArrayList<Member> valgtListe = null;

        System.out.print("Skriv trænerens navn: ");
        String newTrainer = scn.nextLine();

        System.out.println("Vælg aldersgruppe træneren skal knyttes til:");
        System.out.println("1. Under 18");
        System.out.println("2. Over 18");
        System.out.println("0. Gå tilbage");
        System.out.print("Valg: ");

        while (true) {
            String valg = scn.nextLine();
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

    public static void editMemberName(MemberList memberList, Scanner scn) {
        boolean setName = true;

        System.out.print("Skriv medlems ID: ");
        String id = scn.nextLine();

        for (Member m : memberList.getAllMembers()) {
            if (m.getMemberID().equals(id)) {
                System.out.print("Er du sikker på du vil ændre navnet på medlem :" + m.getName() + " [y/n] : ");
                String svar = scn.nextLine();

                if (!svar.equalsIgnoreCase("y")) {
                    break;
                }

                while (setName) {
                    System.out.print("Skriv det ønskede navn: ");
                    String nytNavn = scn.nextLine().trim();

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

    static void editActivityStatus(MemberList memberList, Scanner scn) {
        boolean setActivity = true;
        String svar, statusSvar;
        System.out.print("Skriv medlems ID: ");
        String id = scn.nextLine();
        if (InputValidering.validateUserID(id)) {

            for (Member m : memberList.getAllMembers()) {
                if (m.getMemberID().equals(id)) {
                    System.out.print("Er du sikker på du vil ændre aktivitets status på medlem :" + m.getName() + " [y/n] : ");
                    svar = scn.nextLine();
                    if (svar.equalsIgnoreCase("y")) {
                        while (setActivity) {
                            System.out.println("Hvilken aktivitets status skal: " + m.getName() + " tildeles.");
                            System.out.println("1: Aktiv\n2: Passiv\n3. tilbage til start");
                            System.out.print("Vælg:");
                            statusSvar = scn.nextLine();
                            if (statusSvar.equals("1")) {
                                m.setActivityStatus(true);
                                System.out.println("Du har ændret " + m.getName() + "'s Aktivitetsform til at være aktiv");
                                setActivity = false;
                            } else if (statusSvar.equals("2")) {
                                m.setActivityStatus(false);
                                System.out.println("Du har ændret " + m.getName() + "'s Aktivitetsform til at være passiv");
                                setActivity = false;
                            } else if(statusSvar.equals("3")){
                                return;
                            } else {
                                System.out.println("Ikke et gyldigt valg.");
                                System.out.print("Prøv igen: ");
                            }
                        }
                    } else break;
                }
            }
        } else if(!InputValidering.validateUserID(id)){
        System.out.println("ugyldigt id, prøv igen");}
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

    public static void changeMemberType(ArrayList<Member> allMembers) {
        if (allMembers == null || allMembers == null) return;
        // 2. Find medlem i listen
        System.out.print("Indtast medlems ID: ");
        String id = UI.scn.nextLine();

        // Find medlem manuelt i listen
        Member memberToChange = null;
        for (Member m : allMembers) {
            if (m.getMemberID().equals(id)) {
                memberToChange = m;
                break;
            }
        }

        if (memberToChange == null) {
            System.out.println("Medlem ikke fundet.");
            return;
        }


        // Gem nuværende data
        String name = memberToChange.getName();
        LocalDate dateBirth = memberToChange.getDateOfBirth();
        double totalArrears = memberToChange.getTotalArrears();
        LocalDate createDate = memberToChange.getCreateDate();
        LocalDate nextPayment = memberToChange.getNextPayment();
        boolean isActive = memberToChange.getIsActive();

        Member newMember;

        if (memberToChange instanceof Competitive) {
            // Skift fra Competitive til Casual
            newMember = new Casual(id, name, dateBirth, totalArrears);
            System.out.println(name + " ændret fra Konkurrence til Motionist");

        } else if (memberToChange instanceof Casual) {
            // Skift fra Casual til Competitive
            newMember = new Competitive(id, name, dateBirth, totalArrears);
            System.out.println(name + " ændret fra Motionist til Konkurrence");

        } else {
            System.out.println("Ugyldig medlemstype");
            return;
        }

        // Bevar eksisterende data
        newMember.setCreateDate(createDate);
        newMember.setNextPayment(nextPayment);
        newMember.setActivityStatus(isActive);
        newMember.updateSubscriptionType();

        // Erstat i listen
        for (int i = 0; i < allMembers.size(); i++) {
            if (allMembers.get(i).getMemberID().equals(id)) {
                allMembers.set(i, newMember);
                break;
            }
        }
    }
}
