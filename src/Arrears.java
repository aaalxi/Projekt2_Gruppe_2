import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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

            //hvis paymentDate ligger i fortiden (er overskredet) lig kontigent til
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


    public void addPayment(Scanner scanner) {
        System.out.print("Medlemmets fulde navn: ");
        String name = scanner.nextLine();
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
        double arrears = member.getTotalArrears();
        System.out.println(name + ", Restance: " + arrears + " kr");


        System.out.print("Indtast det indbetalte beløb: ");
        double payment = InputValidering.doubleInRange(scanner,arrears);  //payment between 0 and total arrears

        member.setTotalArrears(member.getTotalArrears() - payment);

        if (member.getTotalArrears() <= 0) {
            member.setTotalArrears(0);
            member.setIsArrears(false);
            membersArrears.remove(member);
            System.out.println(member.getName() + " er fjernet fra restance!");
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
        if (membersArrears.isEmpty()) {
            System.out.println("Listen er tom!");
        }
    }


    public void printMember(Scanner scn) {
        System.out.print("Medlemmets fulde navn: ");
        String name = scn.nextLine();
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