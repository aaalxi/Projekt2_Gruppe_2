import Result.Administration;
import Result.ResultFilehandling;
import java.util.Scanner;

public class UI {
    static Scanner scn = new Scanner(System.in);
    private final MemberList memberList = new MemberList();
    Arrears arrears = new Arrears(memberList.getAllMembers());
    Administration results = new Administration();
    private final String membersFilnavn = "src//Database//Members.txt";
    static boolean chairman = false;
    static boolean trainer = false;
    static boolean cashier = false;


    public void showMainMenu() {
        MemberFileHandling.loadMembers(membersFilnavn, memberList.getAllMembers());
        memberList.addMembersToTeamList();
        arrears.updateArrears();
        ResultFilehandling.loadResult(results.getResultater());
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
                    if(chairman){
                        showChairmanMenu();
                    } else {
                        if(chairmanPass(scn)){
                            showChairmanMenu();
                        }
                    }
                    break;
                case "2":
                    if(trainer) {
                        showTrainerMenu();
                    } else {
                        if(trainerPass(scn)){
                            showTrainerMenu();
                        }
                    }
                    break;
                case "3":
                    if(cashier) {
                        showCashierMenu();
                    } else {
                        if(cashierPass(scn)){
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
                    memberList.printCompetitive(memberList.getUnder18());
                    running = false;
                    break;
                case "2":
                    System.out.println("--- Liste med konkurrencemedlemmer over 18 ---");
                    memberList.printCompetitive(memberList.getOver18());
                    running = false;
                    break;
                case "3":
                    TrainerAdmin.addDiscipline(memberList,scn);
                    MemberFileHandling.saveMembers(membersFilnavn, memberList.getAllMembers());
                    break;
                case "4":
                    TrainerAdmin.removeDiscipline(memberList,scn);
                    MemberFileHandling.saveMembers(membersFilnavn, memberList.getAllMembers());
                    break;
                case "5":
                    memberList.searchMemberName(memberList.getAllMembers(),scn);
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
                    arrears.printMember(scn);
                    break;
                case "3":
                    arrears.addPayment(scn);
                    MemberFileHandling.saveMembers(membersFilnavn, memberList.getAllMembers());
                    break;
                case "4":
                    arrears.sumArrears();
                    break;
                case "5":
                    MemberAdministration.editActivityStatus(memberList, scn);
                    MemberFileHandling.saveMembers(membersFilnavn, memberList.getAllMembers());
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
                    MemberAdministration.createMember(memberList,scn);
                    MemberFileHandling.saveMembers(membersFilnavn, memberList.getAllMembers());
                    break;
                case "2":
                    System.out.print("Skriv medlemmens ID: ");
                    String ID = scn.nextLine();
                    if(MemberAdministration.disintegrateSwimmer(ID, memberList)){
                        MemberFileHandling.removeMember(membersFilnavn, ID);
                    }
                    break;
                case "3":
                    MemberAdministration.addTrainer(memberList, scn);
                    MemberFileHandling.saveMembers(membersFilnavn, memberList.getAllMembers());
                    break;
                case "4":
                    MemberAdministration.editMemberName(memberList, scn);
                    MemberFileHandling.saveMembers(membersFilnavn, memberList.getAllMembers());
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
                    ResultFilehandling.saveResult(results.getResultater());
                    break;
                case "2":
                    results.addTrainingResult(scn);
                    ResultFilehandling.saveResult(results.getResultater());
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

    boolean chairmanPass(Scanner scn) {
        System.out.print("Indtast Password: ");
        String login = scn.nextLine();
        if (!login.equalsIgnoreCase("formand")) {
            System.out.println();
            System.out.println("Hej formand!\nDu har skrevet et forkert password!.\nPrøv venligst igen.");
            return false;
        } else {
            System.out.println("Du er logget ind.");
            chairman = true;
            return true;
        }
    }

    boolean cashierPass(Scanner scn) {
        System.out.print("Indtast Password: ");
        String login = scn.nextLine();
        if (!login.equalsIgnoreCase("kasser")) {
            System.out.println();
            System.out.println("Hej kasser!\nDu har skrevet et forkert password!.\nPrøv venligst igen.");
            return false;
        } else {
            System.out.println("Du er logget ind.");
            cashier = true;
            return true;
        }
    }

    boolean trainerPass(Scanner scn) {
        System.out.print("Indtast Password: ");
        String login = scn.nextLine();
        if (!login.equalsIgnoreCase("træner")) {
            System.out.println();
            System.out.println("Hej træner!\nDu har skrevet et forkert password.\nPrøv venligst igen.");
            return false;
        } else {
            System.out.println("Du er logget ind.");
            trainer = true;
            return true;
        }
    }
}
