import java.util.ArrayList;

public class MemberList {
    private final ArrayList<Member> allMembers = new ArrayList<>();
    private final ArrayList<Member> under18 = new ArrayList<>();
    private final ArrayList<Member> over18 = new ArrayList<>();

    public ArrayList<Member> getAllMembers() {
        return allMembers;
    }

    public ArrayList<Member> getUnder18() {
        return under18;
    }

    public ArrayList<Member> getOver18() {
        return over18;
    }

    public void addMembersToTeamList() {
        under18.clear();
        over18.clear();

        for (Member m : allMembers) {
            if (m.getIsCompetitive() && m.calculateAge() < 18) {
                under18.add(m);
            } else if (m.getIsCompetitive() && m.calculateAge() >= 18) {
                over18.add(m);
            }
        }
    }

    public void printCompetitive(ArrayList<Member> list) {
        list.sort(java.util.Comparator.comparing(Member::getDateOfBirth));
        for (Member m : list) {
            System.out.println("ID: " + m.getMemberID() + ": " + m.getName() + ": " + m.getCurrentAge() + " år");
        }
    }

    public void searchMemberName(ArrayList<Member> liste) {
        boolean running = false;
        String memberSearch = "";
        String memberSearchTest;
        ArrayList<Member> foundMembers = new ArrayList<>();
        System.out.println("---Søg på medlems navn---");
        System.out.println();

        while (!running) {
            System.out.print("Skriv medlemmets navn: ");
            memberSearchTest = UI.scn.next();
            if (InputValidering.isName(memberSearchTest)) {
                memberSearch = memberSearchTest;
                running = true;
            } else {
                System.out.println("Ikke et gyldigt navn. prøv igen.");
                System.out.println();
            }
        }

        for (Member m : liste) {
            if (m.getName().equalsIgnoreCase(memberSearch)) {
                foundMembers.add(m);
            }
        }
        System.out.println("Der er fundet " + foundMembers.size() + " medlemmer med navnet " + memberSearch);
        for (Member fm : foundMembers) {
            System.out.println(fm.getName() + " : " + fm.getMemberID());
        }
    }
}
