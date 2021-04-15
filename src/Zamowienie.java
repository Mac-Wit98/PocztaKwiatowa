
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Zamowienie {
    private LocalDate dataZamowienia;
    private Bukiet bukiet;


    public Zamowienie(LocalDate dataZamowienia, Bukiet bukiet) {
        this.dataZamowienia = dataZamowienia;
        this.bukiet = bukiet;
    }


    public LocalDate getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(LocalDate dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }

    public Bukiet getBukiet() {
        return bukiet;
    }

    public void setBukiet(Bukiet bukiet) {
        this.bukiet = bukiet;
    }



    public static void zapisZamowienia(Zamowienie zamowienie) {
        Magazyn.read();
        try {
            FileWriter fileWriter = new FileWriter("zamówienia.csv", true);
            fileWriter.write(zamowienie.getDataZamowienia() + "," + zamowienie.getBukiet() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Błąd");
            ;
        }
    }

    @Override
    public String toString() {
        return "Zamowienie: " +
                " dataZamowienia = " + dataZamowienia +
                " bukiet=" + bukiet;
    }


}
