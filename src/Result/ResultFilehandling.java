package Result;

import java.io.*;
import java.time.LocalDate;

public class ResultFilehandling {

   // public TrainingRes (String name, String category, LocalDate date, String discipline, int distance, double time) {
     //   super(false, name, category, date, discipline, distance, time);            //isCompetitve = false


    public static void saveResult() {
//skal ikke lukke writer når det står med resources
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src//Database//CompetitionResults.txt"))) {
            for (Result r : Administration.resultater) {

                String line = r.getDate() + ";" + r.getName() + ";" + r.getCategory() + ";" + r.getDiscipline() + ";"
                        + r.getDistance() + ";" + r.getTime() + ";" + r.getIsCompetitive();

                if (r instanceof CompetitionRes comp) {
                    line += ";" + comp.getTournament() + ";" + comp.getPlacement();
                }

                writer.write(line);
                writer.newLine();
            }
            System.out.println("Resultatet er gemt i filen!");
        } catch (IOException e) {
            System.out.println("Fejl: Kunne ikke skrive resultatet til fil!");
        }
    }

    public static void loadResult() {
        Administration.resultater.clear();          //behøver ikke appende writer, loader fra txt
        try (BufferedReader reader = new BufferedReader(new FileReader("src//Database//CompetitionResults.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(";");
                LocalDate date = LocalDate.parse(data[0]);
                String name = data[1];
                String category = data [2].toUpperCase();
                String discipline = data[3].toUpperCase();
                int distance = Integer.parseInt(data[4]);
                double time = Double.parseDouble(data[5]);
                boolean isCompetitive = Boolean.parseBoolean(data[6]);


                if (isCompetitive) {
                    String tournament = data [7];
                    int placement = Integer.parseInt(data[8].toUpperCase());
                    CompetitionRes r = new CompetitionRes(tournament, name, category, date, discipline, distance,
                            time, placement);
                    Administration.resultater.add(r);
                }
                else {
                    TrainingRes r = new TrainingRes(name, category, date, discipline, distance, time);
                    Administration.resultater.add(r);
                }
                line = reader.readLine();  //ny linje
            }

        } catch (IOException e) {
            System.out.println("Kunne ikke indlæse resultater fra fil!" + e.getMessage());
        }
    }




}