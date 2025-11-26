import java.time.LocalDate;

public class Competitive extends Member {

    private String discipline;
    private String trainer;


    public Competitive(String memberID, String name, LocalDate dateBirth, boolean isActive, boolean isArrears,
                       double totalArrears, String casualCompetitive, String discipline, String trainer) {

        super(memberID, name, dateBirth, isActive, isArrears, totalArrears, casualCompetitive);
        this.discipline=discipline;
        this.trainer=trainer;
    }

    public String getDiscipline () {
        return discipline;
    }

    public void setDiscipline (String discipline) {
        this.discipline=discipline;
    }

    public String getTrainer () {
        return trainer;
    }

    public void setTrainer (String trainer) {
        this.trainer=trainer;
    }

}
