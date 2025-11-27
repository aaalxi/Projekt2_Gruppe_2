import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberFileHandling {

    //Lav metoder så medlemmer kan gemmes og loades lokalt fra fil ved system opstart.
    public static void saveMembers(ArrayList<Member> members, String fileName) {
        ArrayList<Member> exsistingMembers = loadMembers(fileName); //Liste af medlemmer som allerede er i txt filen.
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {
            for (Member m : members) {
                boolean exists = false;
                for (Member ex : exsistingMembers) {
                    if (ex.getMemberID().equals(m.getMemberID())) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    String linje = m.getMemberID() + ";" + m.getName() + ";" + m.getDateOfBirth() + ";" + m.getIsCompetitive() + ";" + m.getTotalArrears();
                    pw.println(linje);
                    exsistingMembers.add(m);
                }
            }
            System.out.println("Filen: ("+fileName+") er gemt.");
        } catch (IOException e) {
            System.out.println("Fejl ved gemning: " + e.getMessage());
        }
    }

    public static ArrayList<Member> loadMembers(String fileName) {
        ArrayList<Member> members = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String linje;
            while ((linje = br.readLine()) != null) {
                String[] data = linje.split(";");
                String id = data[0];
                String name = data[1];
                LocalDate dateBirth = LocalDate.parse(data[2]);
                String comp = data[3];
                double totalArrears = Double.parseDouble(data[4]);

                if (comp.equals("true")) {
                    members.add(new Competitive(id, name, dateBirth, totalArrears));
                }
                if (comp.equals("false")) {
                    members.add(new Casual(id, name, dateBirth, totalArrears));
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
        }
        return members;
    }
    //Test main. ***HUSK AT FJERNE INDEN FÆRDIGE***
    public static void main(String[] args) {
        String fil = "Members.txt";
        ArrayList<Member> members = new ArrayList<>();
        System.out.println(members);
        members.addAll(loadMembers(fil));

        for (Member m : members) {
            System.out.println(m.getName());
        }
    }

}
