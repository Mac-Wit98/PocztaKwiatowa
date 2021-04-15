import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;


public class Dostawa {
    static HashMap<String, Integer> stanDostawy = new HashMap<>();

    public static void read() {
        try {
            File file = new File("dostawa.csv");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",", 2);
                if (parts.length == 2) {
                    String key = parts[0];
                    String value = parts[1];
                    stanDostawy.put(key, Integer.valueOf(value));
                } else {
                    System.out.println("Niekompletne bądź złe dane " + scanner.nextLine());
                }

            }
            Magazyn.read();
            Set<String> setOfKeys =  stanDostawy.keySet();
            LinkedList<String> listOfKeys = new LinkedList<>(setOfKeys);
            for (String listOfKey : listOfKeys) {
                if (stanDostawy.containsKey(listOfKey)) {
                    Magazyn.stanMagazynu.replace(listOfKey,Magazyn.stanMagazynu.get(listOfKey),Magazyn.stanMagazynu.get(listOfKey)+stanDostawy.get(listOfKey));
                }
            }
            PrintWriter zapis = new PrintWriter("magazyn.csv");
            for (String listOfKey : listOfKeys) {
                zapis.println(listOfKey + "," + Magazyn.stanMagazynu.get(listOfKey));
            }
            zapis.close();


        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku, sprawdź czy dobrze wprowadzono nazwę do odczytu");

        }
    }


}
