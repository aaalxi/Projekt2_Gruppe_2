import java.time.LocalDate;

public class MemberAdministration {
    public static void addCompetitiveSwimmer(){
        System.out.println("brugerid");
        String userID = UI.scn.nextLine();
        System.out.println("Hvad er navnet?");
        String name = UI.scn.nextLine();
        System.out.println("Hvad er fødselsdato?");
        LocalDate birthday = LocalDate.parse(UI.scn.nextLine());
        MemberList.addCompetitiveMemberToList(userID, name, birthday);
        System.out.println("Bruger lavet for "+name+": "+birthday);
    }

    public static void addCasualSwimmer(){

    }
}
