import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberFileHandling {

    public static void saveMembers(String fileName, ArrayList<Member> members) {

        if(fileName == null || fileName.isBlank()){
            System.out.println("Ugyldigt filnavn");
            return;
        }

        if(members == null){
            System.out.println("Ingen medlemmer at gemme.");
            return;
        }

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
                        + disciplinesString + ";" + m.getCreateDate() + ";" + m.getNextPayment() + ";" + m.getIsActive();
                pw.println(linje);

            }
          //  System.out.println("Filen: (" + fileName + ") er gemt.");
        } catch (IOException e) {
            System.out.println("Fejl ved gemning: " + e.getMessage());
        }
    }
    public static void loadMembers(String fileName, ArrayList<Member> members) {
        members.clear(); //clear for at være sikker på der ikke bliver gemt dups og at arraylisten allMembers er tom.

        Path path = Path.of(fileName);
        if(!Files.exists(path)){
            System.out.println("Filen findes ikke: "+fileName);
        }

        if(!Files.isRegularFile(path)){
            System.out.println("Stien er ikke en fil: "+fileName);
        }

        if(!Files.isReadable(path)){
            System.out.println("Filen kan ikke læses: "+fileName);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linje;
            while ((linje = br.readLine()) != null) {

                if (linje.isBlank()) { //springer tomme linjer over i filen. Så hvis der er en tom linje crasher vi ikke systemet eller skaber out of bounds exseption.
                    continue;
                }
                String[] data = linje.split(";");

                if (data.length < 9) {
                    System.out.println("Fejl i linje (for få felter): " + linje);
                    continue;
                }
                try {
                    String id = data[0];
                    String name = data[1];
                    LocalDate dateBirth = LocalDate.parse(data[2]);
                    boolean isComp = Boolean.parseBoolean(data[3]);
                    double totalArrears = Double.parseDouble(data[4]);
                    String trainer = data.length > 5 ? data[5] : "";

                    //data.length >6 bruges for at sikre at data[6] findes. Hvis den ikke findes erstat med "". Da filformatet kan være ældre eller data kan være ufuldstændigt.
                    String disciplineString = data.length > 6 ? data[6] : "";
                    LocalDate createDate = LocalDate.parse(data[7]);
                    LocalDate nextPayment = LocalDate.parse(data[8]);

                    //Læser isActive fra filen kun hvis feltet findes. data.length>9 sikre at data[9] eksistere så vi undgår IndexOutOfBoundsException.
                    // Hvis feltet mangler (ældre/ufuldstændigt filformat), bliver isActive automatisk false.
                    boolean isActive = data.length > 9 && Boolean.parseBoolean(data[9]);

                    Member m;

                    if (isComp) {
                        Competitive c = new Competitive(id, name, dateBirth, totalArrears);
                        c.setTrainer(trainer);

                        disciplineString = disciplineString.replace("[", "").replace("]", "").trim();
                        if (!disciplineString.isEmpty()) {
                            String[] discipliner = disciplineString.split(", ");
                            for (String d : discipliner) {
                                if (!d.isEmpty()) {
                                    c.addDiscipline(Discipline.valueOf(d));
                                }
                            }
                        }
                        m = c;
                    } else {
                        m = new Casual(id, name, dateBirth, totalArrears);
                    }
                    m.setCreateDate(createDate);
                    m.setNextPayment(nextPayment);
                    if (data.length > 9) {
                        m.setActivityStatus(isActive);
                    }
                    m.updateSubscriptionType();
                    members.add(m);
                } catch (Exception e) {
                    System.out.println("Kunne ikke loade medlem, dårlig data: " + linje);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
        }
    }

    public static void removeMember(String fileName, String ID) {
        if (!InputValidering.validateUserID(ID)) {
            System.out.println("Ugyldigt ID.");
            return;
        }
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()){
            System.out.println("Filen finde ikke: "+ fileName);
            return;
        }
        try {
            ArrayList<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            lines.removeIf(l -> l.contains(ID));
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String s : lines) {
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
} //memberfilehandling
