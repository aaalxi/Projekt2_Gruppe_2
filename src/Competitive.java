import java.time.LocalDate;

public class Competitive extends Member {

    public Competitive(String memberID, String name, LocalDate dateBirth, double totalArrears) {
        super(memberID, name, dateBirth, totalArrears);
        isCompetitive = true;
    }
}
