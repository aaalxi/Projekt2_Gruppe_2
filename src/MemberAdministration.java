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

        System.out.println("Hvad er fødselsdatoen?");
        LocalDate birthday = LocalDate.parse(UI.scn.nextLine());

        if (isCompetitive){
            MemberList.allMembers.add(new Competitive(userID, name, birthday, 0));
        } else {
            MemberList.allMembers.add(new Casual(userID, name, birthday, 0));
        }

        MemberList.addMembersToTeamList();
        System.out.println("Bruger lavet for "+name+": "+birthday);
    }

    public static void addTrainer(){
        ArrayList<Member> valgtListe = null;

        System.out.println("Hvad hedder træneren?");
        String newTrainer = UI.scn.nextLine();

        System.out.println("Vælg aldersgruppe træneren skal knyttes til:");
        System.out.println("1. Under 18");
        System.out.println("2. Over 18");
        System.out.println("0. Gå tilbage");
        String valg = "";
        boolean running = true;
        while(running){
            valg = UI.scn.nextLine();
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

    static boolean validateUserID(String id){
        return id.matches("^[a-z]{4}[0-9]{4}$");
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
        if (validateUserID(newUserID)){
            return newUserID;
        } else {
            System.out.println("Kunne ikke laver ID til brugeren.");
            return "";
        }
    }

    static void addDisciplineToCompetitive(String memberID, Discipline discipline){

        Member fundet = null;
        for(Member m : MemberList.allMembers){
            if (m.getMemberID().equals(memberID)){
                fundet = m;
                break;
            }
        }

        if (fundet == null){
            System.out.println("Medlem ikke fundet.");
            return;
        }

        if (!(fundet instanceof Competitive)){
            System.out.println("Medlemmet er ikke konkurrencesvømmer.");
            return;
        }

        Competitive comp = (Competitive) fundet;
        comp.addDiscipline(discipline);
    }

    static void removeDiscipline(){
        String ID;
        Discipline discipline;
        while (true){ // loop of doom and despair
            System.out.println("Skriv medlems ID:");
            ID = UI.scn.nextLine();

            System.out.println("Vælg disciplin (BUTTERFLY/BACKSTROKE/BREASTSTROKE/FREESTYLE): ");
            String d = UI.scn.nextLine().toUpperCase();

            try{
                discipline = Discipline.valueOf(d);
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Ugyldig disciplin.");
            }
        }
        Member fundet = null;
        for(Member m : MemberList.allMembers){
            if (m.getMemberID().equals(ID)){
                fundet = m;
                break;
            }
        }

        if (fundet == null){
            System.out.println("Medlem ikke fundet.");
            return;
        }

        Competitive comp = (Competitive) fundet;
        comp.removeDiscipline(discipline);
    }
}
