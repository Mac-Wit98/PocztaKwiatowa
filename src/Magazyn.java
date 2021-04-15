import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Magazyn {
    static HashMap<String, Integer> stanMagazynu = new HashMap<>();

    @Override
    public String toString() {
        return "Magazyn : \n" +
                stanMagazynu;
    }

    public Magazyn() {
        read();
    }

    public static void read() {
        try {
            File file = new File("magazyn.csv");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",", 2);
                if (parts.length == 2) {
                    String key = parts[0];
                    String value = parts[1];
                    stanMagazynu.put(key, Integer.valueOf(value));
                } else {
                    System.out.println("Niekompletne bądź złe dane " + scanner.nextLine());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku, sprawdź czy dobrze wprowadzono nazwę do odczytu");
        }
    }

}
