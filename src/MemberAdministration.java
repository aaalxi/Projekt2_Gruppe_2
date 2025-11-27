import java.time.LocalDate;

public class MemberAdministration {
    public static void addCompetitiveSwimmer(){
        System.out.println("brugerid");
        String userID = UI.scn.nextLine();
        System.out.println("Hvad er navnet?");
        String name = UI.scn.nextLine();
        System.out.println("Hvad er fødselsdatoen?");
        LocalDate birthday = LocalDate.parse(UI.scn.nextLine());
        MemberList.addCompetitiveMemberToList(userID, name, birthday);
        MemberFileHandling.saveMembers(MemberList.allMembers, "Delfinen");
        System.out.println("Bruger lavet for "+name+": "+birthday);
    }

    public static void addCasualSwimmer(){
        System.out.println("brugerid");
        String userID = UI.scn.nextLine();
        System.out.println("Hvad er navnet?");
        String name = UI.scn.nextLine();
        System.out.println("Hvad er fødselsdatoen?");
        LocalDate birthday = LocalDate.parse(UI.scn.nextLine());
        MemberList.addCasualMemberToList(userID, name, birthday);
        MemberFileHandling.saveMembers(MemberList.allMembers, "Delfinen");
        System.out.println("Bruger lavet for "+name+": "+birthday);
    }

    public static void main(String[] args) {
        addCasualSwimmer();
        addCompetitiveSwimmer();
        System.out.println(MemberList.over18);
        System.out.println(MemberList.allMembers);
    }
}
