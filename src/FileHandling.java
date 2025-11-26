import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileHandling {

    //Lav metoder så medlemmer kan gemmes og loades lokalt fra fil ved system opstart.
    public static ArrayList<String> readFromFile(String fileName) {

        try {
            return Files.readAllLines(Path.of(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Kunne ikke læse " + fileName + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
