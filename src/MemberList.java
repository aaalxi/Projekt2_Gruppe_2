import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    //Test main. ***HUSK AT FJERNE INDEN FÆRDIGE***

    //Print arrayliste med u18 og o18 konkurrencemedlemmer ud metoder:
    public static void printCompetitive (ArrayList <Member> list, String filename) {
        list.sort(Comparator.comparing(Member::getDateOfBirth));

        try (PrintWriter writer = new PrintWriter("scr/" + filename)) {
            for (Member m : list) {
                writer.println(m);
            }
            System.out.println(filename + "er skrevet til src!");
        } catch (FileNotFoundException e) {
            System.out.println("Fejl ved udskrivning til " + filename);
        }
    }


    public static void main(String[] args) {
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
