import java.time.LocalDate;

public class Competitive extends Member {

    public Competitive(String memberID, String name, LocalDate dateBirth) {
        super(memberID, name, dateBirth);
        isCompetitive = true;
    }
}
