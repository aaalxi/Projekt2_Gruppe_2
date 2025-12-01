import java.time.LocalDate;
import java.util.ArrayList;

public class Competitive extends Member {

    private final ArrayList<Discipline> disciplines = new ArrayList<>();

    public Competitive(String memberID, String name, LocalDate dateBirth, double totalArrears) {
        super(memberID, name, dateBirth, totalArrears);
        isCompetitive = true;
    }

    public void addDiscipline(Discipline discipline){
        if (!)
    }
}
