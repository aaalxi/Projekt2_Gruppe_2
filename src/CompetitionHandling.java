import java.util.ArrayList;
import java.util.Scanner;

class Result {
    String name;
    String discipline;
    int distance;
    double time;
    int placement;
    String kategory;

    public Result(String name, String discipline, int distance, double time,
                  int placement, String kategory) {
        this.name = name;
        this.discipline = discipline;
        this.distance = distance;
        this.time = time;
        this.placement = placement;
        this.kategory = kategory;
    }
}
    public class CompetitionHandling {
        private ArrayList<Result> resultater = new ArrayList<>();
        private Scanner scn = new Scanner(System.in);

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
        }