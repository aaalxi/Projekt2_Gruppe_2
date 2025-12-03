import java.time.LocalDate;
import java.util.ArrayList;

public class Arrears {

    private static double payment=0;
    private static ArrayList <Member> members = MemberList.allMembers;
    private static ArrayList <Member> membersArrears = new ArrayList<>();
    private static LocalDate today = LocalDate.now();


    public static void addArrears () {             //skal være static eller ikke?

        for (Member m : members) {
            if (m.getIsArrears()) continue;

            if (m.getCreateDate().plusYears(1).isBefore(today)){
                membersArrears.add(m);
                m.setTotalArrears(Subscription.getYearlyQuota(m));
                m.setIsArrears(true);
            }
        }
    }

    public static void sumArrears () {
        double sum=0;
        for (Member m : members) {
            if (m.getTotalArrears() > 0) {
                sum += m.getTotalArrears();
            }
        }
        System.out.println("Samlede restance d. " + LocalDate.now() + " = " + sum + " kr");
    }


    public static void printArrears () {
        membersArrears.clear();
        for (Member m : members) {
            if (m.getTotalArrears() > 0) {
                membersArrears.add(m);
            }
        }
        for (Member m : membersArrears) {
            System.out.println(m.printArrears());
        }
    }


    public static void addPayment () {
        System.out.println("Medlemmets fulde navn: ");
        String name = UI.scn.nextLine();
        Member member = null;

        for (Member m : members) {

            if (m.getName().equalsIgnoreCase(name)) {
                member=m;
                break;
            }
        }

        if (member == null) {
            System.out.println("Medlem ikke fundet!");
            return;
        }

        System.out.println("Indbetalte beløb: ");
        payment = UI.scn.nextDouble();
        member.setTotalArrears(member.getTotalArrears()-payment);
        UI.scn.nextLine();

        if (member.getTotalArrears() <= 0) {
            member.setTotalArrears(0);
            member.setIsArrears(false);
            membersArrears.remove(member);
        }
        System.out.println("Indbetalingen er registreret!");
        MemberFileHandling.saveMembers("Members.txt");
    }

}