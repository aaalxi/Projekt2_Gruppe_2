import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberFileHandling {

    //Lav metoder så medlemmer kan gemmes og loades lokalt fra fil ved system opstart.
    public static void saveMembers(String fileName) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Member m : MemberList.allMembers) {
                String trainer = "";
                String disciplinesString = "";
                if (m instanceof Competitive comp){
                    trainer = comp.getTrainer();
                    disciplinesString = comp.getDisciplines().toString();
                }

                String linje = m.getMemberID() + ";" + m.getName() + ";" + m.getDateOfBirth() +
                        ";" + m.getIsCompetitive() + ";" + m.getTotalArrears() + ";" + trainer + ";" + disciplinesString;
                pw.println(linje);

            }
            System.out.println("Filen: (" + fileName + ") er gemt.");
        } catch (IOException e) {
            System.out.println("Fejl ved gemning: " + e.getMessage());
        }
    }

    public static void loadMembers(String fileName) {
        MemberList.allMembers.clear();


        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linje;
            while ((linje = br.readLine()) != null) {
                String[] data = linje.split(";");
                String id = data[0];
                String name = data[1];
                LocalDate dateBirth = LocalDate.parse(data[2]);
                String comp = data[3];
                double totalArrears = Double.parseDouble(data[4]);

                if (comp.equals("true")) {
                    Competitive m = new Competitive(id, name, dateBirth, totalArrears);
                    MemberList.allMembers.add(m);

                    String trainer = data.length > 5 ? data[5] : "";
                    m.setTrainer(trainer);


                    String disciplineString = data.length > 6 ? data[6] : "";
                    disciplineString = disciplineString.replace("[", "").replace("]", "").trim();

                    if (!disciplineString.isEmpty()) {
                        String[] discipliner = disciplineString.split(", ");

                        for (String d : discipliner) {
                            if (!d.isEmpty()) {
                                m.addDiscipline(Discipline.valueOf(d));
                            }
                        }
                    }
                }
                if (comp.equals("false")) {
                    Member m = new Casual(id, name, dateBirth, totalArrears);
                    MemberList.allMembers.add(m);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
        }
    }

    //Test main. ***HUSK AT FJERNE INDEN FÆRDIGE***
    public static void main(String[] args) {
        String fil = "Members.txt";
        ArrayList<Member> members = new ArrayList<>();
        System.out.println(members);
//        members.addAll(loadMembers(fil));

        for (Member m : members) {
            System.out.println(m.getName());
        }
    }

} //memberfilehandling
