import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class MemberAdministration {
    private static final Random gen = new Random();

    public static void addSwimmer(boolean isCompetitive){
        System.out.println("Hvad er navnet?");
        String name = UI.scn.nextLine();

        String userID = createUserID(name);
        if (userID.isEmpty()){
            System.out.println("Der blev ikke lavet et id til brugeren. Prøv igen.");
            return;
        }

        System.out.println("Hvad er fødselsdatoen? yyyy-mm-dd");
        LocalDate birthday = LocalDate.parse(UI.scn.nextLine());

        if (isCompetitive){
            MemberList.allMembers.add(new Competitive(userID, name, birthday, 0));
        } else {
            MemberList.allMembers.add(new Casual(userID, name, birthday, 0));
        }

        MemberList.addMembersToTeamList();
        System.out.println("Bruger lavet for "+name+": "+birthday);
    }


    public static boolean disintegrateSwimmer(String ID){
        boolean removedFromAll = MemberList.allMembers.removeIf(a -> a.getMemberID().equals(ID));
        boolean removedFromUnder = MemberList.under18.removeIf(a -> a.getMemberID().equals(ID));
        boolean removedFromOver = MemberList.over18.removeIf(a -> a.getMemberID().equals(ID));

        if (removedFromOver){
            System.out.println("Fjernede "+ID+" fra Over18 listen og fra AllMembers listen.");
        }

        if (removedFromUnder){
            System.out.println("Fjernede "+ID+" fra Under18 listen og fra AllMembers listen.");
        }

        if (removedFromAll && !removedFromOver && !removedFromUnder){
            System.out.println("Fjernede "+ID+" fra AllMembers listen.");
        }

        if(!removedFromOver && !removedFromUnder && !removedFromAll){
            System.out.println(ID+" er ikke et id i databasen.");
        }
        return removedFromAll || removedFromOver || removedFromUnder;
    }

    public static void addTrainer(){
        ArrayList<Member> valgtListe = null;

        System.out.print("Skriv trænerens navn: ");
        String newTrainer = UI.scn.nextLine();

        System.out.println("Vælg aldersgruppe træneren skal knyttes til:");
        System.out.println("1. Under 18");
        System.out.println("2. Over 18");
        System.out.println("0. Gå tilbage");
        System.out.print("Valg: ");
        boolean running = true;
        while(running){
            String valg = UI.scn.nextLine();
            switch (valg){
                case "1":
                    valgtListe = MemberList.under18;
                    running = false;
                    break;
                case "2":
                    valgtListe = MemberList.over18;
                    running = false;
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Prøv og skriv det igen");
            }
        }

        for (Member m : valgtListe){
            Competitive c = (Competitive) m;
            c.setTrainer(newTrainer);
        }
        System.out.println(newTrainer+" er nu tilknyttet alle konkurrencesvømmere i denne aldersgruppe.");
    }

    static String createUserID(String name){
        int space = name.indexOf(" ");
        String firstTwo = name.substring(0, 2).toLowerCase();
        String lastTwo;
        if (space==-1){
            lastTwo = "zz";
        } else {
            lastTwo = name.substring(space+1, space+3).toLowerCase();
        }
        String newUserID = firstTwo + lastTwo + gen.nextInt(10) + gen.nextInt(10) + gen.nextInt(10) + gen.nextInt(10);
        if (InputValidering.validateUserID(newUserID)){
            return newUserID;
        } else {
            System.out.println("Kunne ikke laver ID til brugeren.");
            return "";
        }
    }

    static void editMemberName() {
        boolean setName = true;
        String svar, nytNavn;
        System.out.print("Skriv medlems ID: ");
        String id = UI.scn.nextLine();

        for (Member m : MemberList.allMembers) {
            if (m.getMemberID().equals(id)) {
                System.out.print("Er du sikker på du vil ændre navn på medlem :" + m.getName() + " [y/n] : ");
                svar = UI.scn.nextLine();
                if (svar.equalsIgnoreCase("y")) {
                    while (setName) {
                        System.out.print("Skriv det ønskede navn: ");
                        nytNavn = UI.scn.nextLine();
                        nytNavn = nytNavn.trim();
                        if (InputValidering.isName(nytNavn)) {
                            System.out.println("Du har ændret "+m.getName()+" til "+nytNavn);
                            m.setName(nytNavn);
                            setName = false;
                        } else {
                            System.out.println("Ikke et gyldigt navn.");
                            System.out.print("Prøv igen: ");
                        }
                    }
                }
                else break;
            }
        }
    }
    static void editActivityStatus() {
        boolean setActivity = true;
        String svar, statusSvar;
        System.out.print("Skriv medlems ID: ");
        String id = UI.scn.nextLine();
        if (InputValidering.validateUserID(id)) {

            for (Member m : MemberList.allMembers) {
                if (m.getMemberID().equals(id)) {
                    System.out.print("Er du sikker på du vil ændre aktivitets status på medlem :" + m.getName() + " [y/n] : ");
                    svar = UI.scn.nextLine();
                    if (svar.equalsIgnoreCase("y")) {
                        while (setActivity) {
                            System.out.println("Hvilken aktivitets status skal: " + m.getName() + " tildeles.");
                            System.out.println("1: Aktiv\n2: Passiv\n3. tilbage til start");
                            System.out.print("Vælg:");
                            statusSvar = UI.scn.nextLine();
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
    public static void createMember() {
        boolean loop = true;
        while(loop){
            System.out.println("Er den nye medlem konkurrencesvømmer eller motionist?\n"+
                    "1. Konkurrencesvømmer\n"+
                    "2. Motionist\n"+
                    "0. Gå tilbage");
            System.out.print("Valg: ");
            String valg = UI.scn.nextLine();
            switch (valg) {
                case "1":
                    loop = false;
                    addSwimmer(true);
                    break;
                case "2":
                    loop = false;
                    addSwimmer(false);
                    break;
                case "0":
                    loop = false;
                    break;
                default:
                    System.out.println("Ikke et valg, prøv igen.");
            }
        }
    }
}
