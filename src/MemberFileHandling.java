import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberFileHandling {

    public static void saveMembers(String fileName, ArrayList<Member> members) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Member m : members) {
                String trainer = "";
                String disciplinesString = "";
                if (m instanceof Competitive comp) { // hvis m er Competitive, castes den automatisk til comp så man ikke behøver skrive (Competitive)m
                    trainer = comp.getTrainer();
                    disciplinesString = comp.getDisciplines().toString();
                }
// Her laves der en string som er en samling af de relevante data fra m og printet ind i en textfil
                String linje = m.getMemberID() + ";" + m.getName() + ";" + m.getDateOfBirth() +
                        ";" + m.getIsCompetitive() + ";" + m.getTotalArrears() + ";" + trainer + ";"
                        + disciplinesString + ";" + m.getCreateDate() + ";" + m.getNextPayment() +";"+ m.getIsActive();
                pw.println(linje);

            }
            System.out.println("Filen: (" + fileName + ") er gemt.");
        } catch (IOException e) {
            System.out.println("Fejl ved gemning: " + e.getMessage());
        }
    }

    public static void loadMembers(String fileName, ArrayList<Member> members) {
        members.clear(); //clear for at være sikker på der ikke bliver gemt dups og at arraylisten allMembers er tom.


        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linje;
            while ((linje = br.readLine()) != null) {
                String[] data = linje.split(";");
                String id = data[0];
                String name = data[1];
                LocalDate dateBirth = LocalDate.parse(data[2]);
                String comp = data[3];
                double totalArrears = Double.parseDouble(data[4]);
                LocalDate createDate = LocalDate.parse(data[7]);
                LocalDate nextPayment = LocalDate.parse(data[8]);

                if (comp.equals("true")) {
                    Competitive m = new Competitive(id, name, dateBirth, totalArrears);

                    String trainer = data.length > 5 ? data[5] : "";
                    m.setTrainer(trainer);


                    String disciplineString = data.length > 6 ? data[6] : ""; //Da træner og discipline er "valgfrit" tjekker vi om det data findes og hvis det ikke gør så skrives der ""
                    disciplineString = disciplineString.replace("[", "").replace("]", "").trim();

                    if (!disciplineString.isEmpty()) {
                        String[] discipliner = disciplineString.split(", ");
                        for (String d : discipliner) {
                            if (!d.isEmpty()) {
                                m.addDiscipline(Discipline.valueOf(d));
                            }
                        }
                    }
                    m.setCreateDate(createDate);
                    m.setNextPayment(nextPayment);
                    members.add(m);
                }
                if (comp.equals("false")) {
                    Member m = new Casual(id, name, dateBirth, totalArrears);
                    m.setCreateDate(createDate);
                    m.setNextPayment(nextPayment);
                    members.add(m);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
        }
    }

    public static void removeMember(String fileName, String ID){
        try {
            ArrayList<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine();
            while (line != null){
                lines.add(line);
                line = reader.readLine();
            }
            lines.removeIf(l -> l.contains(ID));
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String s : lines){
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
} //memberfilehandling
