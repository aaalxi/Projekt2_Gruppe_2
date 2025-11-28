import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MemberList {
    static ArrayList<Member> allMembers = new ArrayList<>();
    static ArrayList<Member> under18 = new ArrayList<>();
    static ArrayList<Member> over18 = new ArrayList<>();

    public static void addMembersToTeamList(){
        for(Member m : allMembers){
            if (m.isCompetitive){
                if(m.calculateAge()<18){
                    under18.add(m);
                } else {
                    over18.add(m);
                }
            }
        }
    }


    //Print arrayliste med u18 og o18 konkurrencemedlemmer til konsol:
    public static void printCompetitive (ArrayList <Member> list) {
        list.sort(Comparator.comparing(Member::getDateOfBirth));         //sætter ældst øverst, ellers .reversed
        for (Member m : list) {
            System.out.println("ID: "+ m.getMemberID() +": " + m.getName() + ": " + m.getCurrentAge() +" år");
        }
    }

    public static void searchMemberName(ArrayList<Member> liste){
        String memberSearch;
        ArrayList<Member> foundMembers = new ArrayList<>();
        System.out.println("---Søg på medlems navn---");
        System.out.println();
        System.out.print("Skriv medlemmets navn: ");
        memberSearch = UI.scn.next();

        for ( Member m : liste){
            if (m.getName().equalsIgnoreCase((memberSearch))){
                foundMembers.add(m);
            }
        }
        System.out.println("Der er fundet "+ foundMembers.size()+" medlemmer med navnet "+memberSearch);
        for(Member fm : foundMembers){
            System.out.println(fm.getName()+" : "+fm.getMemberID());
        }
    }
    //Test main. ***HUSK AT FJERNE INDEN FÆRDIGE***
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String fil = "Members.txt";
        MemberList e = new MemberList();
        Member m1 = new Casual("m2","Rune",LocalDate.of(1992,12,11), 0);
        Member m2 = new Competitive("m3","Bo",LocalDate.of(2010,12,11), 0);
        Member m3 = new Competitive("m4","Per",LocalDate.of(2010,12,11), 0);
        Member m4 = new Competitive("m5","Lars",LocalDate.of(2010,12,11), 0);
        Member m5 = new Competitive("m6","Lis",LocalDate.of(2000,12,11), 0);

        System.out.println(allMembers);

        allMembers.add(m1);
        allMembers.add(m2);
        MemberFileHandling.saveMembers(fil);
        allMembers.clear();
        allMembers.add(m3);
        allMembers.add(m4);
        allMembers.add(m5);
        System.out.println(allMembers);
        MemberFileHandling.saveMembers(fil);
        allMembers.clear();
        allMembers.add(m1);
        allMembers.add(m2);
        MemberFileHandling.saveMembers(fil);
        for(Member m : allMembers){
            System.out.println(m.getDateOfBirth());
            System.out.println(m.isCompetitive);
        }
        System.out.println();
        e.addMembersToTeamList();
        System.out.println("Kørt sortMembersInlist metode");
        System.out.println();
        System.out.println("Print for comp under 18");
        for(Member m : e.under18){
            System.out.println(m.getDateOfBirth());
            System.out.println(m.isCompetitive);
        }
        System.out.println();
        System.out.println("Print for comp over 18");
        for(Member m : e.over18){
            System.out.println(m.getDateOfBirth());
            System.out.println(m.isCompetitive);
        }
    }
}
