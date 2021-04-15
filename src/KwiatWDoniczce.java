import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

public class KwiatWDoniczce extends Bukiet{
    private int iloscStorczykow;
    final double cenaStorczyka = 117.5;
    public KwiatWDoniczce(int iloscTulipanow, int iloscLilii, int iloscRoz, int iloscStorczykow) {
        super(iloscTulipanow, iloscLilii, iloscRoz);
        this.iloscStorczykow = iloscStorczykow;
    }

    public int getIloscStorczykow() {
        return iloscStorczykow;
    }

    public double getCenaStorczyka() {
        return this.iloscStorczykow*cenaStorczyka;
    }
    @Override
    public boolean dostepnosc() {
        Magazyn.read();
        ArrayList<Integer> ilosc = new ArrayList<>(Magazyn.stanMagazynu.values());
        int iloscStorwMagazynie = ilosc.get(3);
        if (iloscStorwMagazynie >= this.iloscStorczykow) {
            return true;
        } else {
            return false;
        }
    }
        static void odejmijZMagazynu(int iloscTulipanow, int iloscLilii, int iloscRoz, int iloscStorczykow) {
            try {
                Magazyn.read();
                Magazyn.stanMagazynu.replace("Tulipan", Magazyn.stanMagazynu.get("Tulipan"), Magazyn.stanMagazynu.get("Tulipan") - iloscTulipanow);
                Magazyn.stanMagazynu.replace("Lilie", Magazyn.stanMagazynu.get("Lilie"), Magazyn.stanMagazynu.get("Lilie") - iloscLilii);
                Magazyn.stanMagazynu.replace("Róża", Magazyn.stanMagazynu.get("Róża"), Magazyn.stanMagazynu.get("Róża") - iloscRoz);
                Magazyn.stanMagazynu.replace("Storczyk", Magazyn.stanMagazynu.get("Storczyk"),Magazyn.stanMagazynu.get("Storczyk")-iloscStorczykow);
                PrintWriter zapis = new PrintWriter("magazyn.csv");
                Set<String> setOfKeys = Magazyn.stanMagazynu.keySet();
                LinkedList<String> listOfKeys = new LinkedList<>(setOfKeys);
                PrintWriter zapisUbytku = new PrintWriter("magazyn.csv");
                for (String listOfKey : listOfKeys) {
                    zapisUbytku.println(listOfKey + "," + Magazyn.stanMagazynu.get(listOfKey));
                }
                zapisUbytku.close();
            } catch (FileNotFoundException e) {
                System.out.println("Nie znaleziono pliku");
            }

        }

    @Override
    public String toString() {
        return "KwiatWDoniczce["+"iloscStorczykow="+iloscStorczykow+"; cenaStorczyka="+cenaStorczyka*iloscStorczykow +']';
    }
}
