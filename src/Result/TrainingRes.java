package Result;

import java.time.LocalDate;

public class TrainingRes extends Result {


    public TrainingRes (String name, String category, LocalDate date, String discipline, int distance, double time) {
        super(false, name, category, date, discipline, distance, time);
    }
}
