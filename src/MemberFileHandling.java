import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberFileHandling {

    //Lav metoder så medlemmer kan gemmes og loades lokalt fra fil ved system opstart.
    public static void saveMembers(ArrayList<Member> members, String fileName) {

        try {
            PrintWriter pw = new PrintWriter(fileName);
            for (Member m : members) {
                String linje = m.getMemberID() + ";" + m.getName() + ";" + m.getDateOfBirth() + ";" + m.getIsCompetitive() + ";" + m.getTotalArrears();
                pw.println(linje);
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Fejl ved gemning: " + e.getMessage());
        }
    }


    public static ArrayList<Member> loadMembers(String fileName) {
        ArrayList<Member> members = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String linje;
            while ((linje = br.readLine()) != null) {
                String[] data = linje.split(";");
                String id = data[0];
                String name = data[1];
                LocalDate dateBirth = LocalDate.parse(data[2]);
                double totalArrears = Double.parseDouble(data[4]);
                String comp = data[3];

                if (comp.equals("true")) {
                    members.add(new Competitive(id, name, dateBirth, totalArrears));
                }
                if (comp.equals("false")) {
                    members.add(new Casual(id, name, dateBirth, totalArrears));
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
        }
        return members;
    }

    public static void main(String[] args) {
        String fil = "Members.txt";
        ArrayList<Member> members = new ArrayList<>();
        System.out.println(members);
        members.addAll(loadMembers(fil));

        for(Member m : members){
            System.out.println(m.getName());
        }
    }

} //memberfilehandling
