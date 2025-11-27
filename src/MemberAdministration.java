import java.time.LocalDate;

public class MemberAdministration {
    public static void addCompetitiveSwimmer(){
        System.out.println("brugerid");
        String userID = UI.scn.nextLine();
        System.out.println("Hvad er navnet?");
        String name = UI.scn.nextLine();
        System.out.println("Hvad er fødselsdatoen?");
        LocalDate birthday = LocalDate.parse(UI.scn.nextLine());
        MemberList.allMembers.add(new Competitive(userID, name, birthday, 0));
        MemberList.addMembersToTeamList();
        MemberFileHandling.saveMembers("Members.txt");
        System.out.println("Bruger lavet for "+name+": "+birthday);
    }

    public static void addCasualSwimmer(){
        System.out.println("brugerid");
        String userID = UI.scn.nextLine();
        System.out.println("Hvad er navnet?");
        String name = UI.scn.nextLine();
        System.out.println("Hvad er fødselsdatoen?");
        LocalDate birthday = LocalDate.parse(UI.scn.nextLine());
        MemberList.allMembers.add(new Casual(userID, name, birthday, 0));
        MemberList.addMembersToTeamList();
        MemberFileHandling.saveMembers("Members.txt");
        System.out.println("Bruger lavet for "+name+": "+birthday);
    }

    public static void main(String[] args) {
        addCasualSwimmer();
        addCompetitiveSwimmer();
        System.out.println(MemberList.over18);
        System.out.println(MemberList.allMembers);
    }
}
