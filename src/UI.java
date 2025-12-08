import Result.Administration;
import Result.ResultFilehandling;

import java.util.Scanner;

public class UI {
    static Scanner scn = new Scanner(System.in);
    Arrears arrears = new Arrears(MemberList.allMembers);
    String membersFilnavn = "src//Database//Members.txt";
    static boolean chairmanPass = false;
    static boolean trainerPass = false;
    static boolean cashierPass = false;


    public void showMainMenu() {
        MemberFileHandling.loadMembers(membersFilnavn);
        MemberList.addMembersToTeamList();
        arrears.updateArrears();
        ResultFilehandling.loadResult();
        boolean running = true;
        AsciiArt.printDelfin1();
        while (running) {
            System.out.println("=== Hovedmenu ===\n" +
                    "1. Formand menu\n"+
                    "2. Træner menu\n"+
                    "3. Kasserer menu\n"+
                    "4. Stævne menu\n"+
                    "0. Afslut");
            System.out.print("Vælg et punkt: ");
            String valg = scn.nextLine();

            switch (valg) {
                case "1":
                    if(chairmanPass){
                        showChairmanMenu();
                    } else {
                        if(UiService.chairmanPass()){
                            showChairmanMenu();
                        }
                    }
                    break;
                case "2":
                    if(trainerPass) {
                        showTrainerMenu();
                    } else {
                        if(UiService.trainerPass()){
                            showTrainerMenu();
                        }
                    }
                    break;
                case "3":
                    if(cashierPass) {
                        showCashierMenu();
                    } else {
                        if(UiService.cashierPass()){
                            showCashierMenu();
                        }
                    }
                    break;
                case "4":
                    competitionMenu();
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
                    "3. Tilknyt disciplin til en konkurrencesvømmer\n" +
                    "4. Fjern disciplin fra medlem\n" +
                    "5. Søg på medlem via navn.\n" +
                    "0. Tilbage til Hovedmenu");
            System.out.print("Vælg: ");
            String valg = scn.nextLine();

            switch (valg) {
                case "1":
                    System.out.println("--- Liste med konkurrencemedlemmer under 18 ---");
                    MemberList.printCompetitive(MemberList.under18);
                    running = false;
                    break;
                case "2":
                    System.out.println("--- Liste med konkurrencemedlemmer over 18 ---");
                    MemberList.printCompetitive(MemberList.over18);
                    running = false;
                    break;
                case "3":
                    TrainerAdmin.addDiscipline();
                    MemberFileHandling.saveMembers(membersFilnavn);
                    break;
                case "4":
                    TrainerAdmin.removeDiscipline();
                    MemberFileHandling.saveMembers(membersFilnavn);
                    break;
                case "5":
                    MemberList.searchMemberName(MemberList.allMembers);
                    scn.nextLine();
                    System.out.println();
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
                    "2. Søg efter en medlem i restance\n" +
                    "3. Ændring af restance\n" +
                    "4. Total restance\n" +
                    "5. Ændring af aktivitets status\n" +
                    "0. Tilbage til Hovedmenu");
            System.out.print("Vælg: ");
            String valg = scn.nextLine();

            switch (valg) {
                case "1":
                    arrears.printAll();
                    break;
                case "2":
                    arrears.printMember();
                    break;
                case "3":
                    arrears.addPayment();
                    MemberFileHandling.saveMembers(membersFilnavn);
                    break;
                case "4":
                    arrears.sumArrears();
                    break;
                case "5":
                    MemberAdministration.editActivityStatus();
                    MemberFileHandling.saveMembers(membersFilnavn);
                    break;
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
            System.out.println("""
                    --- Formand Menu ---
                    1. Opret medlem
                    2. Fjern medlem
                    3. Tilknyt ny træner til hold
                    4. Rediger medlems navn
                    0. Tilbage til Hovedmenu\s""");
            System.out.print("Vælg:");
            String valg = scn.nextLine();

            switch (valg) {
                case "1":
                    MemberAdministration.createMember();
                    MemberFileHandling.saveMembers(membersFilnavn);
                    break;
                case "2":
                    System.out.print("Skriv medlemmens ID: ");
                    String ID = UI.scn.nextLine();
                    if(MemberAdministration.disintegrateSwimmer(ID)){
                        MemberFileHandling.removeMember(membersFilnavn, ID);
                    }
                    break;
                case "3":
                    MemberAdministration.addTrainer();
                    MemberFileHandling.saveMembers(membersFilnavn);
                    break;
                case "4":
                    MemberAdministration.editMemberName();
                    MemberFileHandling.saveMembers(membersFilnavn);
                    break;
                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }

    /**
     * UI Menu-metode der håndtere stævneInformation
     * lader træner oprette og slette stævnedata
     */
    public void competitionMenu() {
        Administration results = new Administration();
        boolean running = true;
        while (running) {
            System.out.println("--- StævneMenu ---\n" +
                    "1. Ny stævnedata\n" +
                    "2. Ny Træningsdata\n" +
                    "3. Se Stævnedata\n" +
                    "4: Se Træningsdata\n"+
                    "5: Se top 5 i Kategori\n"+
                    "6. Slet stævnedata\n" +
                    "0. Tilbage til Hovedmenu");
            System.out.print("Vælg: ");
            String valg = scn.nextLine();

            switch (valg) {
                case "1":
                    results.addCompetitiveResult(scn);
                    ResultFilehandling.saveResult();
                    break;
                case "2":
                    results.addTrainingResult(scn);
                    ResultFilehandling.saveResult();
                    break;
                case "3":
                    results.printCompetitive();
                    break;
                case "4":
                    results.printTraining();
                    break;
                case "5":
                    results.topFive(scn);
                    break;
                case "6":
                    results.deleteResults(scn);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
            }

        }
    }
}
