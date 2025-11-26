import java.time.LocalDate;

public class Casual extends Member {


    public Casual (String memberID, String name, LocalDate dateBirth, boolean isActive, boolean isArrears,
                   double totalArrears, String casualCompetitive) {

        super(memberID, name,dateBirth,isActive,isArrears, totalArrears,casualCompetitive);
    }

}