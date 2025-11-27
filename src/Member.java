import java.time.LocalDate;
import java.time.Period;

abstract class Member {

    private final String memberID;
    private String name;
    private LocalDate dateBirth;
    private boolean isActive, isArrears;
    private double totalArrears;
    private String casualCompetitive;
    private int currentAge;
    private LocalDate createDate;
    private AgeStatus ageStatus;
    private int lastArreasUpdateDate;




    public Member (String memberID, String name,LocalDate dateBirth, boolean isActive, boolean isArrears,
                   double totalArrears, String casualCompetitive, int currentAge, LocalDate createDate,
                   AgeStatus ageStatus,int lastArreasUpdateDate) {
        this.memberID = memberID;
        this.name=name;
        this.dateBirth=dateBirth;
        this.isActive=isActive;
        this.isArrears=isArrears;
        this.totalArrears=totalArrears;
        this.casualCompetitive=casualCompetitive;
        this.currentAge=currentAge;
        this.createDate=createDate;
        this.ageStatus=ageStatus;
        this.lastArreasUpdateDate=lastArreasUpdateDate;
    }
    public int calculateAge(){ // udregner alder ud fra fødselsdato af medlem
        Period currentAge = Period.between(dateBirth, LocalDate.now());
        return currentAge.getYears();
    }
    public void setAgeStatus(AgeStatus ageStatus){
        this.ageStatus=ageStatus;
    }
    public AgeStatus getAgeStatus(){
        return ageStatus;
    }
    public void setArrears(double totalArrears){
        this.totalArrears=totalArrears;
    }

    public String getMemberID () {
        return memberID;
    }
    public LocalDate getCreateDate(){
        return createDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateBirth () {
        return dateBirth;
    }

    public boolean isActive () {
        return isActive;
    }

    public boolean isArrears () {
        return isArrears;
    }

    public double getTotalArrears () {
        return totalArrears;
    }

    public String getCasualCompetitive () {
        return casualCompetitive;
    }
    public int getCurrentAge(){
        return currentAge;
    }


}