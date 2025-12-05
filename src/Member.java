import java.time.LocalDate;
import java.time.Period;

public abstract class Member {

    private final String memberID;
    private String name;
    private LocalDate dateOfBirth;
    private boolean isActive, isArrears;
    private double totalArrears;
    private LocalDate nextPayment;
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
        this.createDate=LocalDate.now();
        this.nextPayment=createDate.plusYears(1);
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
    public boolean getIsActive(){
        return isActive;
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
    public void setCreateDate (LocalDate createDate) {
        this.createDate=createDate;
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

    public void setName(String name){
        this.name = name;
    }

    public boolean getIsArrears () {
        return isArrears;
    }

    public void setIsArrears (boolean isArrears) {
        this.isArrears=isArrears;
    }

    public double getTotalArrears(){
        return totalArrears;
    }

    public void setTotalArrears (double totalArrears) {
        this.totalArrears=totalArrears;
    }

    public LocalDate getNextPayment () {
        return nextPayment;
    }

    public void setNextPayment (LocalDate nextDate) {
        this.nextPayment=nextDate;
    }

    public boolean getIsCompetitive(){
        return isCompetitive;
    }

    public String toString(){
        return name + " - Status: " + getSubscriptionType().toString();
    }

    public String printArrears () {
        return createDate + ": " + name + " --- MemberID: " + memberID + " --- Restance: " + totalArrears + " kr.";
    }
}