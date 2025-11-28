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
            if (m.isCompetitive && m.calculateAge() < 18){
                boolean exists = false;
                for(Member u : under18){
                    if(m.getMemberID().equals(u.getMemberID())){
                        exists = true;
                        break;
                    }
                }

                if (!exists){
                    under18.add(m);
                }
            } else if (m.isCompetitive && m.calculateAge() >= 18){
                boolean exists = false;
                for(Member u : over18){
                    if(m.getMemberID().equals(u.getMemberID())){
                        exists = true;
                        break;
                    }
                }

                if(!exists){
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
        boolean running = false;
        String memberSearch= "";
        String memberSearchTest;
        ArrayList<Member> foundMembers = new ArrayList<>();
        System.out.println("---Søg på medlems navn---");
        System.out.println();

        while(!running){
            System.out.print("Skriv medlemmets navn: ");
            memberSearchTest = UI.scn.next();
            if(isName(memberSearchTest)){
                memberSearch = memberSearchTest;
                running = true;
            }
            else{
                System.out.println("Ikke et gyldigt navn. prøv igen.");
                System.out.println();
            }

        }
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

    /**
     * Denne metode tjekker om en String er et gyldigt navn.
     * Ved at se om det indeholder a-z stor og småt samt æøå - og mellemrum og min 1 tegn.
     * @param s
     * @return true hvis ikke s er tom og indeholder kravne.
     */
    public static boolean isName(String s){
        return s != null && s.matches("[a-zA-ZæøåÆØÅ\\- ]+");
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

        addMembersToTeamList();
        System.out.println(under18);
        System.out.println(over18);
        addMembersToTeamList();
        System.out.println(under18);
        System.out.println(over18);
    }
}
