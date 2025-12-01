import java.time.LocalDate;
import java.util.Collections;
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
        MemberFileHandling.saveMembers("Members.txt");
        System.out.println("Bruger lavet for "+name+": "+birthday);
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

        MemberFileHandling.saveMembers("Members.txt");
    }

    public static void main(String[] args) {
        addSwimmer(true);
        System.out.println(MemberList.over18);
        System.out.println(MemberList.allMembers);
    }
}
