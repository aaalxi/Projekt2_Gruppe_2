public class UiService {
    public static void chairmanPass() {
        boolean deniedPass = true;
        while (deniedPass) {
            System.out.println("indtast Password: ");
            String login = UI.scn.nextLine();
            if (!login.equalsIgnoreCase("formand")) {
                System.out.println("forkert Password.\n Prøv venligst igen.");
            } else {
                System.out.println("du er logget ind.");
                deniedPass=false;
            }
        }
    }
    public static void cashierPass(){
    boolean deniedPass = true;
        while (deniedPass) {
        System.out.println("indtast Password: ");
        String login = UI.scn.nextLine();
        if (!login.equalsIgnoreCase("kasser")) {
            System.out.println("forkert Password.\n Prøv venligst igen.");
        } else {
            System.out.println("du er logget ind.");
            deniedPass=false;
        }
    }
}
    public static void trainerPass(){
    boolean deniedPass = true;
        while (deniedPass) {
        System.out.println("indtast Password: ");
        String login = UI.scn.nextLine();
        if (!login.equalsIgnoreCase("træner")) {
            System.out.println("forkert Password.\n Prøv venligst igen.");
        } else {
            System.out.println("du er logget ind.");
            deniedPass=false;
        }
    }
}
}
