import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Bukiet {
    private int iloscRoz;
    private int iloscTulipanow;
    private int iloscLilii;

    final double cenaRoz = 3.52;
    final double cenaTulipanow = 2.70;
    final double cenaLilii = 4;

    public Bukiet(int iloscTulipanow, int iloscLilii, int iloscRoz) {
        Magazyn.read();
        this.iloscTulipanow = iloscTulipanow;
        this.iloscLilii = iloscLilii;
        this.iloscRoz = iloscRoz;
    }

    public int getIloscRoz() {
        return iloscRoz;
    }

    public void setIloscRoz(int iloscRoz) {
        this.iloscRoz = iloscRoz;
    }

    public int getIloscTulipanow() {
        return iloscTulipanow;
    }

    public void setIloscTulipanow(int iloscTulipanow) {
        this.iloscTulipanow = iloscTulipanow;
    }

    public int getIloscLilii() {
        return iloscLilii;
    }

    public void setIloscLilii(int iloscLilii) {
        this.iloscLilii = iloscLilii;
    }

    public double getCenaBukietu() {
        return this.getIloscLilii() * cenaLilii + this.getIloscRoz() * cenaRoz + this.getIloscTulipanow() * cenaTulipanow;
    }

    public boolean dostepnosc() {
        Magazyn.read();
        ArrayList<Integer> ilosc = new ArrayList<>(Magazyn.stanMagazynu.values());
        int iloscTulipanowWMagazynie = ilosc.get(0);
        int iloscLiliiWMagazynie = ilosc.get(1);
        int iloscRozWMagazynie = ilosc.get(2);
        if (iloscLiliiWMagazynie >= this.iloscLilii
                && iloscRozWMagazynie >= this.iloscRoz
                && iloscTulipanowWMagazynie >= this.iloscTulipanow) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Bukiet[" + "Roza : " + this.getIloscRoz() +
                ";Tulipan : " + this.getIloscTulipanow() +
                ";Lilia : " + this.getIloscLilii() +
                "; Cena : " + this.getCenaBukietu() + "]";
    }

    static void odejmijZMagazynu(int iloscTulipanow, int iloscLilii, int iloscRoz) {
        try {
            Magazyn.read();
            Magazyn.stanMagazynu.replace("Tulipan", Magazyn.stanMagazynu.get("Tulipan"), Magazyn.stanMagazynu.get("Tulipan") - iloscTulipanow);
            Magazyn.stanMagazynu.replace("Lilie", Magazyn.stanMagazynu.get("Lilie"), Magazyn.stanMagazynu.get("Lilie") - iloscLilii);
            Magazyn.stanMagazynu.replace("Róża", Magazyn.stanMagazynu.get("Róża"), Magazyn.stanMagazynu.get("Róża") - iloscRoz);
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


}
