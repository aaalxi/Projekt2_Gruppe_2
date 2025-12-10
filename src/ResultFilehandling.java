import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ResultFilehandling {


    public static void saveResult(ArrayList<Result> results) {
//skal ikke lukke writer når try + resources
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src//Database//CompetitionResults.txt"))) {
            for (Result r : results) {

                String line = r.getDate() + ";" + r.getName() + ";" + r.getCategory() + ";" + r.getDiscipline() + ";"
                        + r.getDistance() + ";" + r.getTime() + ";" + r.getIsCompetitive();

                if (r instanceof ResultsCompetitive comp) {
                    line += ";" + comp.getTournament() + ";" + comp.getPlacement();
                }

                writer.write(line);
                writer.newLine();
            }
         //   System.out.println("Resultatet er gemt i filen!");
        } catch (IOException e) {
            System.out.println("Fejl: Kunne ikke skrive resultatet til fil!");
        }
    }


    public static void loadResult(ArrayList<Result> results) {
        results.clear();          //behøver ikke appende writer, loader fra txt
        try (BufferedReader reader = new BufferedReader(new FileReader("src//Database//CompetitionResults.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(";");
                LocalDate date = LocalDate.parse(data[0]);
                String name = data[1];
                String category = data[2].toUpperCase();
                String discipline = data[3].toUpperCase();
                int distance = Integer.parseInt(data[4]);
                double time = Double.parseDouble(data[5]);
                boolean isCompetitive = Boolean.parseBoolean(data[6]);

                if (isCompetitive) {
                    String tournament = data[7];
                    int placement = Integer.parseInt(data[8].toUpperCase());
                    ResultsCompetitive r = new ResultsCompetitive(tournament, name, category, date, discipline, distance,
                            time, placement);
                    results.add(r);
                } else {
                    ResultsTraining r = new ResultsTraining(name, category, date, discipline, distance, time);
                    results.add(r);
                }
                line = reader.readLine();  //læs ny linje
            }

        } catch (IOException e) {
            System.out.println("Kunne ikke indlæse resultater fra fil!" + e.getMessage());
        }
    }
}