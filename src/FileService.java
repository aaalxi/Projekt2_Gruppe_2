import java.util.ArrayList;

public class FileService {
    public static Member findByID(String memberID, ArrayList<Member> allMembers) {
        if (memberID == null || memberID.isBlank()) return null;  // tjek input
        for (Member m : allMembers) {
            if (m.getMemberID().equalsIgnoreCase(memberID)) { // ignoreCase for fleksibilitet
                return m;  // medlem fundet
            }
        }
        System.out.println("intet medlem fundet!");
        return null;  // medlem ikke fundet
    }
}
