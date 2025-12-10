import java.time.LocalDate;

public class ResultsTraining extends Result {


    public ResultsTraining(String name, String category, LocalDate date, Discipline discipline, int distance, double time) {
        super(false, name, category, date, discipline, distance, time);
    }
}
