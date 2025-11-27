import java.time.LocalDate;
import java.time.Period;

abstract class Member {

    private final String memberID;
    private String name;
    private LocalDate dateOfBirth;
    private boolean isActive, isArrears;
    private double totalArrears;
    boolean isCompetitive;
    private int currentAge;
    private LocalDate createDate;
    private SubscriptionType subscriptionType;

    public Member (String memberID, String name,LocalDate dateBirth, double totalArrears) {
        this.memberID = memberID;
        this.name=name;
        this.dateOfBirth=dateBirth;
        this.isActive=true;
        this.isArrears=false;
        this.totalArrears=totalArrears;
        this.currentAge=calculateAge();
        updateAgeStatus();
    }

    public void updateAgeStatus(){
        if(isActive){
            if(getCurrentAge() < 18){
                setSubscriptionType(SubscriptionType.Under18);
            } else if (getCurrentAge() < 60) {
                setSubscriptionType(SubscriptionType.Over18);
            } else {
                setSubscriptionType(SubscriptionType.Over60);
            }
        } else {
            setSubscriptionType(SubscriptionType.Passive);
        }

    }

    public int calculateAge(){ // udregner alder ud fra fødselsdato af medlem
        Period currentAge = Period.between(dateOfBirth, LocalDate.now());
        return currentAge.getYears();
    }

    public int getCurrentAge(){
        return currentAge;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType){
        this.subscriptionType = subscriptionType;
    }

    public SubscriptionType getSubscriptionType(){
        return subscriptionType;
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

    public String toString(){
        return name + ": " ;
    }
}