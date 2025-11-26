import java.time.LocalDate;

public class Competitive extends Member {

    public Competitive(String memberID, String name, LocalDate dateBirth,
                       boolean isActive, boolean isArrears, double totalArrears) {
        super(memberID, name, dateBirth, isActive, isArrears, totalArrears);
        isCompetitive = true;
    }
}
