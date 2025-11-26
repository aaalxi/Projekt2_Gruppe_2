import java.time.LocalDate;

abstract class Member {

    private final String memberID;
    private String name;
    private LocalDate dateOfBirth;
    private boolean isActive, isArrears;
    private double totalArrears;
    boolean isCompetitive;


    public Member (String memberID, String name,LocalDate dateBirth, boolean isActive, boolean isArrears,
                   double totalArrears) {
        this.memberID = memberID;
        this.name=name;
        this.dateOfBirth=dateBirth;
        this.isActive=isActive;
        this.isArrears=isArrears;
        this.totalArrears=totalArrears;
    }

    public LocalDate getDateOfBirth () {
        return dateOfBirth;
    }
}