import java.time.LocalDate;

public class Competitive extends Member {
    private String trainer;

    public Competitive(String memberID, String name, LocalDate dateBirth, double totalArrears) {
        super(memberID, name, dateBirth, totalArrears);
        isCompetitive = true;
    }

    public String getTrainer(){
        return trainer;
    }

    public void setTrainer(String trainer){
        this.trainer = trainer;
    }
}
