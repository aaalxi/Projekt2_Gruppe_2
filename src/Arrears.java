import java.time.LocalDate;
import java.util.ArrayList;

public class Arrears {
    private LocalDate today = LocalDate.now();
    private ArrayList<Member> members;
    private ArrayList<Member> membersArrears;


    public Arrears(ArrayList<Member> members) {
        this.members = members;
        this.membersArrears = new ArrayList<>();
    }

    public void updateArrears() {
        for (Member m : members) {

            //hvis paymentDate ligger i fortiden (er overskredet)
            if (today.isAfter(m.getNextPayment())) {
                double yearlyFee = getYearlyQuota(m);
                m.setTotalArrears(m.getTotalArrears() + yearlyFee);
                m.setIsArrears(true);
                m.setNextPayment(m.getNextPayment().plusYears(1));

                if (!membersArrears.contains(m)) {
                    membersArrears.add(m);
                }
            }
            if (!membersArrears.contains(m) && m.getTotalArrears() > 0) {
                membersArrears.add(m);
            }
        }
    }


    public void addPayment() {
        System.out.println("Medlemmets fulde navn: ");
        String name = UI.scn.nextLine();
        Member member = null;

        for (Member m : membersArrears) {

            if (m.getName().equalsIgnoreCase(name)) {
                member = m;
                break;
            }
        }

        if (member == null) {
            System.out.println("Medlem ikke fundet!");
            return;
        }

        System.out.println(name + ", Restance: " + member.getTotalArrears() + " kr");

        System.out.println("Indtast det indbetalte beløb: ");
        double payment = UI.scn.nextDouble();
        UI.scn.nextLine();

        member.setTotalArrears(member.getTotalArrears() - payment);

        if (member.getTotalArrears() <= 0) {
            member.setTotalArrears(0);
            member.setIsArrears(false);
            membersArrears.remove(member);
        }
        System.out.println("Indbetalingen er registreret!");
    }


    public void sumArrears() {
        updateArrears();
        double sum = 0;

        for (Member m : membersArrears) {
            sum += m.getTotalArrears();
        }
        System.out.println("Samlede restance d. " + LocalDate.now() + " = " + sum + " kr");
    }


    public void printAll() {
        updateArrears();
        System.out.println("--- RESTANCER ---");
        for (Member m : membersArrears) {
            System.out.println(m.printArrears());
        }
    }


    public void printMember() {
        System.out.println("Medlemmets fulde navn: ");
        String name = UI.scn.nextLine();
        boolean found = false;

        for (Member m : membersArrears) {
            if (m.getName().equalsIgnoreCase(name)) {
                System.out.println(name + ", Beløbet: " + m.getTotalArrears() + " kr");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Kunne ikke finde medlemmet i restance!");
        }
    }

    public double getYearlyQuota(Member member){
        switch (member.getSubscriptionType()){
            case Under18: return 1000;
            case Over18: return 1600;
            case Over60: return 1200;
            case Passive: return 500;
            default:
        }
        return 0;
    }
}