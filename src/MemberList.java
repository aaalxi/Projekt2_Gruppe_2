import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class MemberList {
    private static ArrayList<Member> allMembers = new ArrayList<>();
    private ArrayList<Member> under18 = new ArrayList<>();
    private ArrayList<Member> over18 = new ArrayList<>();

    public int calculateAge(LocalDate birthdate){ // udregner alder ud fra fødselsdato af medlem
        Period currentAge = Period.between(birthdate, LocalDate.now());
        return currentAge.getYears();
    }

    public void sortMembersinList(){
        for(Member m : allMembers){
            if (m.isCompetitive){
                if(calculateAge(m.getDateOfBirth())<18){
                    under18.add(m);
                } else {
                    over18.add(m);
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
