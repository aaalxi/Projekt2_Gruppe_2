import java.time.LocalDate;
import java.time.Period;

abstract class Member {

    private final String memberID;
    private String name;
    private LocalDate dateOfBirth;
    private boolean isActive, isArrears;
    private double totalArrears;
    boolean isCompetitive;

    public Member (String memberID, String name,LocalDate dateBirth) {
        this.memberID = memberID;
        this.name=name;
        this.dateOfBirth=dateBirth;
        this.isActive=true;
        this.isArrears=false;
        this.totalArrears=0;
    }

    public int calculateAge(){ // udregner alder ud fra fødselsdato af medlem
        Period currentAge = Period.between(dateOfBirth, LocalDate.now());
        return currentAge.getYears();
    }

    public LocalDate getDateOfBirth () {
        return dateOfBirth;
    }
}