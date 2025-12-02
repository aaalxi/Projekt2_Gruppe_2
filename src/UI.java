import java.util.Scanner;

public class UI {
    static Scanner scn = new Scanner(System.in);

    public void showMainMenu() {
        MemberFileHandling.loadMembers("Members.txt");
        MemberList.addMembersToTeamList();
        boolean running = true;
        while (running) {
            System.out.println("=== Hovedmenu ===");
            System.out.println("1. Formand-menu");
            System.out.println("2. Træner-menu");
            System.out.println("3. Kasserer-menu");
            System.out.println("0. Afslut");
            System.out.print("Vælg et punkt: ");

            String valg = scn.nextLine();

            switch (valg) {
                case "1":
                    showChairmanMenu();
                    break;
                case "2":
                    showTrainerMenu();
                    break;
                case "3":
                    showCashierMenu();
                    break;
                case "0":
                    running = false;
                    System.out.println("Programmet afsluttes.");
                    break;
                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
            }
        }//while running loop

    }

    public void showTrainerMenu() {
        boolean running = true;
        while (running) {
            System.out.println("--- TrænerMenu ---\n" +
                    "1. Se liste over konkurrencesvømmere U18\n" +
                    "2. Se liste over konkurrencesvømmere O18\n" +
                    "3. Tilknyt disciplin til en konkurrencesvømmer" +
                    "4. Fjern disciplin fra medlem\n"+
                    "5. Søg på medlem via navn.\n" +
                    "6. vis stævnemenu.\n"+
                    "0. Tilbage til Hovedmenu");
            System.out.print("Vælg: ");
            String valg = scn.nextLine();

            switch (valg) {
                case "1":
                    System.out.println("--- Liste med konkurrencemedlemmer under 18 ---");
                    MemberList.printCompetitive(MemberList.under18);
                    running=false;
                    break;
                case "2" :
                    System.out.println("--- Liste med konkurrencemedlemmer over 18 ---");
                    MemberList.printCompetitive(MemberList.over18);
                    running=false;
                    break;
                case "3":
                    addDiscipline();
                    break;
                case "4":
                    removeDiscipline();
                case "5":
                    MemberList.searchMemberName(MemberList.allMembers);
                    scn.nextLine();
                    System.out.println();
                    break;
                case "6":
                    competitionMenu();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }

    public void showCashierMenu() {
        boolean running = true;
        while (running) {
            System.out.println("--- Kasser Menu ---\n" +
                    "1. Oversigt over medlemmer i restance\n" +
                    "2. Ændring af restance\n" +
                    "3. Total restance\n" +
                    "0. Tilbage til Hovedmenu\n"+
                    "Vælg: ");
            String valg = scn.nextLine();

            switch (valg) {
                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }

    public void showChairmanMenu() {
        boolean running = true;
        while (running) {
            System.out.println("--- Formand Menu ---");
            System.out.println("1. Opret Medlem");
            System.out.println("2. Tilknyt ny træner til hold");
            System.out.println("0. Tilbage til Hovedmenu");
            System.out.print("Vælg: ");
            String valg = scn.nextLine();

            switch (valg) {
                case "1":
                    createMember();
                    break;
                case "2":
                    trainerChange();
                    break;
                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }

    public void competitionMenu(){
        CompetitionHandling competitionHandling = new CompetitionHandling();
        boolean running = true;
        while (running){
            System.out.println("--- StævneMenu ---\n"+
                    "1. Indtast ny stævnedata.\n"+
                    "2. se stævnedata\n"+
                    "3. idk man\n"+
                    "4. maybe funktion, maybe not funktion\n"+
                    "0. Tilbage til Hovedmenu\n"+
                    "Vælg: ");
            String valg = scn.nextLine();

            switch (valg){
                case "0":
                    running = false;
                    break;
                case "1":
                    competitionHandling.addResults();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Ugyldigt valg");
            }

        }
    }

    public void createMember(){
        boolean loop = true;
        while(loop){
            System.out.println("Er den nye medlem konkurrencesvømmer eller motionist?");
            System.out.println("1) Konkurrencesvømmer");
            System.out.println("2) Motionist");
            System.out.println("3) Gå tilbage");
            String valg = scn.nextLine();
            switch(valg){
                case "1":
                    loop = false;
                    MemberAdministration.addSwimmer(true);
                    break;
                case "2":
                    loop = false;
                    MemberAdministration.addSwimmer(false);
                    break;
                case "3":
                    loop = false;
                    break;
                default:
                    System.out.println("Ikke et valg, prøv igen.");
            }
        }
    }

    public void trainerChange(){
        MemberAdministration.addTrainer();
    }

    public void addDiscipline(){
        System.out.print("Skriv medlems ID: ");
        String id = scn.nextLine();

        System.out.print("Vælg disciplin (BUTTERFLY/BACKSTROKE/BREASTSTROKE/FREESTYLE): ");
        String d = scn.nextLine().toUpperCase();

        try{
            Discipline discipline = Discipline.valueOf(d);
            MemberAdministration.addDisciplineToCompetitive(id,discipline);
        } catch (IllegalArgumentException e){
            System.out.println("Ugyldig disciplin.");
        }
    }

    public void removeDiscipline(){
        MemberAdministration.removeDiscipline();
    }
    //Test main. ***HUSK AT FJERNE INDEN FÆRDIGE***
    public static void main(String[] args) {
        UI a = new UI();
        a.showMainMenu();
    }
}
