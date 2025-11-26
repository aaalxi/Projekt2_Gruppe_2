import java.time.LocalDate;

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

    public LocalDate getDateOfBirth () {
        return dateOfBirth;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public boolean getIsCompetitive(){
        return isCompetitive;
    }
}