import java.time.LocalDate;
import java.time.Period;

abstract class Member {

    private final String memberID;
    private String name;
    private LocalDate dateOfBirth;
    private boolean isActive, isArrears;
    private double totalArrears;
    boolean isCompetitive;
    private int age;


    public Member (String memberID, String name,LocalDate dateBirth) {
        this.memberID = memberID;
        this.name=name;
        this.dateOfBirth=dateBirth;
        this.isActive=true;
        this.isArrears=false;
        this.totalArrears=0;
        this.age = calculateAge(dateBirth);
    }

    public int calculateAge(LocalDate birthdate){ // udregner alder ud fra fødselsdato af medlem
        Period currentAge = Period.between(birthdate, LocalDate.now());
        return currentAge.getYears();
    }

    public LocalDate getDateOfBirth () {
        return dateOfBirth;
    }

    public int getAge(){
        return age;
    }
}