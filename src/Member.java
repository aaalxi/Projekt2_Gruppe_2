import java.time.LocalDate;
import java.time.Period;

abstract class Member {

    private final String memberID;
    private String name;
    private LocalDate dateOfBirth;
    private boolean isActive, isArrears;
    private double totalArrears;
    boolean isCompetitive;

    public Member (String memberID, String name,LocalDate dateBirth, double totalArrears) {
        this.memberID = memberID;
        this.name=name;
        this.dateOfBirth=dateBirth;
        this.isActive=true;
        this.isArrears=false;
        this.totalArrears=totalArrears;
    }

    public int calculateAge(){ // udregner alder ud fra fødselsdato af medlem
        Period currentAge = Period.between(dateOfBirth, LocalDate.now());
        return currentAge.getYears();
    }

    public LocalDate getCreateDate(){
        return createDate;
    }

    public LocalDate getDateOfBirth () {
        return dateOfBirth;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public double getTotalArrears(){
        return totalArrears;
    }

    public boolean getIsCompetitive(){
        return isCompetitive;
    }
}