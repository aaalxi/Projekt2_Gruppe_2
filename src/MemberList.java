import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MemberList {
    static ArrayList<Member> allMembers = new ArrayList<>();
    static ArrayList<Member> under18 = new ArrayList<>();
    static ArrayList<Member> over18 = new ArrayList<>();

    public static void addMembersToTeamList() {
        for (Member m : allMembers) {
            if (m.isCompetitive && m.calculateAge() < 18) {
                boolean exists = false;
                for (Member u : under18) {
                    if (m.getMemberID().equals(u.getMemberID())) {
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    under18.add(m);
                }
            } else if (m.isCompetitive && m.calculateAge() >= 18) {
                boolean exists = false;
                for (Member u : over18) {
                    if (m.getMemberID().equals(u.getMemberID())) {
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    over18.add(m);
                }
            }
        }
    }


    //Print arrayliste med u18 og o18 konkurrencemedlemmer til konsol:
    public static void printCompetitive(ArrayList<Member> list) {
        list.sort(Comparator.comparing(Member::getDateOfBirth));         //sætter ældst øverst, ellers .reversed
        for (Member m : list) {
            System.out.println("ID: " + m.getMemberID() + ": " + m.getName() + ": " + m.getCurrentAge() + " år");
        }
    }


    public static void searchMemberName(ArrayList<Member> liste) {
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
            if (m.getName().equalsIgnoreCase((memberSearch))) {
                foundMembers.add(m);
            }
        }
        System.out.println("Der er fundet " + foundMembers.size() + " medlemmer med navnet " + memberSearch);
        for (Member fm : foundMembers) {
            System.out.println(fm.getName() + " : " + fm.getMemberID());
        }
    }
}
