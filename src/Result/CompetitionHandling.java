package Result;

import java.util.ArrayList;
import java.util.Scanner;

    public class CompetitionHandling {
        private ArrayList<Result> resultater = new ArrayList<>();
        private Scanner scn = new Scanner(System.in);

        /**
         * denne metode opretter et objekt Resultat
         * og tilføjer det til en arraylist Resultater.
         */
        public void addResults(){
            System.out.println("Navn: ");
            String name=scn.nextLine();

            System.out.println("Disciplin: ");
            String discipline=scn.nextLine();

            System.out.println("Distance: ");
            int distance=Integer.parseInt(scn.nextLine());

            System.out.println("Tid: ");
            double time=Double.parseDouble(scn.nextLine());

            System.out.println("Placering: ");
            int placement=Integer.parseInt(scn.nextLine());

            System.out.println("kategori: ");
            String kategory=scn.nextLine();

            Result r= new Result(name, discipline,distance,time,placement,kategory);
            resultater.add(r);

            System.out.println("Resultat tilføjet til kategorien: " + kategory);
            }

        /**
         * Metode til at slette stævneResultater
         * går ind med et for-loop i resultater Arraylisten,
         * og leder efter index for et stævne resultat.
         * fjerner derefter objektet fra arraylisten.
         */
        public void deleteResults(){
            if (resultater.isEmpty()){
                System.out.println("der er ingen resultater at slette!");
                return;
            }
                System.out.println("hvilket resultat vil du gerne slette?");
            for(int i = 0; i<resultater.size(); i++){
                Result r = resultater.get(i);

                System.out.println(i+": "+r.name+", "+r.discipline+": "+r.distance+"M, Tid"+r.time+"S. Placering: "+ r.placement+". Kategori"+r.kategory);
            }
                System.out.println("Indtast nummeret på resultatet");
            String valg = scn.nextLine();

            try{
                int index = Integer.parseInt(valg);

                if(index<0){
                    System.out.println("ugyldigt nummer, prøv igen");
                    return;
                }
                if(index>resultater.size()){
                    System.out.println("ugyldigt nummer, prøv igen");
                    return;
                }
                Result removed = resultater.remove(index);
                System.out.println("Resultat fjernet for "+removed.name);


            } catch (NumberFormatException e){
                System.out.println("du skal indtaste et tal");
            }
            }
        }