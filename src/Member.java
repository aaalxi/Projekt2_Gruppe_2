import java.time.LocalDate;

abstract class Member {

    private final String memberID;
    private String name;
    private LocalDate dateBirth;
    private boolean isActive, isArrears;
    private double totalArrears;
    private String casualCompetitive;


    public Member (String memberID, String name,LocalDate dateBirth, boolean isActive, boolean isArrears,
                   double totalArrears, String casualCompetitive) {
        this.memberID = memberID;
        this.name=name;
        this.dateBirth=dateBirth;
        this.isActive=isActive;
        this.isArrears=isArrears;
        this.totalArrears=totalArrears;
        this.casualCompetitive=casualCompetitive;
    }

    public String getMemberID () {
        return memberID;
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


}