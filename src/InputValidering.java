public class InputValidering {

    static boolean validateUserID(String id){
        return id.matches("^[a-z]{4}[0-9]{4}$");
    }

    /**
     * Denne metode tjekker om en String er et gyldigt navn.
     * Ved at se om det indeholder a-z stor og småt samt æøå - og mellemrum og min 1 tegn.
     *
     * @param s
     * @return true hvis ikke s er tom og indeholder kravne.
     */
    public static boolean isName(String s) {
        return s != null && s.matches("[a-zA-ZæøåÆØÅ\\- ]+");
    }
}
