import java.util.Scanner;

public class UIService {
    public static boolean chairmanPass(Scanner scn) {
        System.out.print("indtast Password: ");
        String login = scn.nextLine();
        if (!login.equalsIgnoreCase("formand")) {
            System.out.println();
            System.out.println("Hej formand!\nDu har skrevet et forkert password!.\nPrøv venligst igen.");
            return false;
        } else {
            System.out.println("du er logget ind.");
            UI.chairmanPass = true;
            return true;
        }
    }

    public static boolean cashierPass(Scanner scn) {
        System.out.print("indtast Password: ");
        String login = scn.nextLine();
        if (!login.equalsIgnoreCase("kasser")) {
            System.out.println();
            System.out.println("Hej kasser!\nDu har skrevet et forkert password!.\nPrøv venligst igen.");
            return false;
        } else {
            System.out.println("du er logget ind.");
            UI.cashierPass = true;
            return true;
        }
    }

    public static boolean trainerPass(Scanner scn) {
        System.out.print("indtast Password: ");
        String login = scn.nextLine();
        if (!login.equalsIgnoreCase("træner")) {
            System.out.println();
            System.out.println("Hej træner!\nDu har skrevet et forkert password.\nPrøv venligst igen.");
            return false;
        } else {
            System.out.println("du er logget ind.");
            UI.trainerPass = true;
            return true;
        }
    }
}
