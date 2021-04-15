import java.beans.BeanDescriptor;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class Sklep {
    static LocalDate dateNow = LocalDate.now();

    public static void main(String[] args) {
        LinkedList<LocalDate> listaDat = new LinkedList<>();
        LinkedList<LocalDate> delayDate = new LinkedList<>();
        int i = 0;
        int counter = 0;

        while (i != 365) {

            try {
                Magazyn.read();
                System.out.println(dateNow.plusDays(i) + " Witaj w Poczcie Kwiatowej !!!\n Wybierz opcje: \n" +
                        "1. Stwórz bukiet i zamówienie \n" +
                        "2 Zamów kwiat doniczkowy\n" +
                        "3 Sprawdź stan zamówienie\n" +
                        "4 Kolejny dzień\n" +
                        "5 Zakończ\n");

                Scanner scanner = new Scanner(System.in);
                String choice = scanner.next();
                switch (choice) {
                    case "1":

                        System.out.println("Podaj ilość tulipanów : ");
                        int tulipany = scanner.nextInt();
                        System.out.println("POdaj ilość lili : ");
                        int lilie = scanner.nextInt();
                        System.out.println("Podaj ilość róż : ");
                        int roze = scanner.nextInt();
                        Bukiet bukiet = new Bukiet(tulipany, lilie, roze);
                        Bukiet bukietOpozniony = new Bukiet(tulipany, lilie, roze);
                        if (bukiet.dostepnosc()) {
                            Bukiet.odejmijZMagazynu(bukiet.getIloscTulipanow(), bukiet.getIloscLilii(), bukiet.getIloscRoz());
                            System.out.println("Podaj datę realizacji zamowienia w formie :" + LocalDate.now());
                            System.out.println("Podaj rok");
                            int year = scanner.nextInt();
                            System.out.println("Podaj miesiąc");
                            int month = scanner.nextInt();
                            System.out.println("Podaj dzień");
                            int day = scanner.nextInt();
                            LocalDate dateOfOrder = LocalDate.of(year, month, day);
                            while (!dateOfOrder.isAfter(LocalDate.now()) || listaDat.contains(dateOfOrder)) {
                                System.out.println("Podj rok");
                                year = scanner.nextInt();
                                System.out.println("Podaj miesiąc");
                                month = scanner.nextInt();
                                System.out.println("Podaj dzień");
                                day = scanner.nextInt();
                                dateOfOrder = LocalDate.of(year, month, day);
                            }
                            listaDat.add(dateOfOrder);
                            Zamowienie zamowienie = new Zamowienie(dateOfOrder, bukiet);
                            Zamowienie.zapisZamowienia(zamowienie);

                        } else {
                            System.out.println("Nie wystarczająca ilość kwiatów proszę czekąć na dostawe w dniu " + LocalDate.now().plusDays(3 + counter));
                            LocalDate delay = LocalDate.now().plusDays(3 + counter);
                            bukietOpozniony = new Bukiet(tulipany, lilie, roze);
                            delayDate.add(delay);
                            Zamowienie zamowienie = new Zamowienie(LocalDate.now().plusDays(3 + counter), bukietOpozniony);
                            Zamowienie.zapisZamowienia(zamowienie);
                            if (bukietOpozniony.dostepnosc()) {
                                Bukiet.odejmijZMagazynu(bukietOpozniony.getIloscTulipanow(), bukietOpozniony.getIloscLilii(), bukietOpozniony.getIloscRoz());
                            }
                            counter += 2;

                        }
                        break;
                    case "2":
                        System.out.println("Podaj ilość Storczyków : ");
                        int storczyk = scanner.nextInt();
                        KwiatWDoniczce kwiatWDoniczce = new KwiatWDoniczce(0, 0, 0, storczyk);
                        KwiatWDoniczce kwiatWDoniczceOpozniony;
                        if (kwiatWDoniczce.dostepnosc()) {
                            KwiatWDoniczce.odejmijZMagazynu(0, 0, 0, storczyk);
                            System.out.println("Podaj datę realizacji zamowienia w formie :" + LocalDate.now());
                            System.out.println("Podaj rok");
                            int year = scanner.nextInt();
                            System.out.println("Podaj miesiąc");
                            int month = scanner.nextInt();
                            System.out.println("Podaj dzień");
                            int day = scanner.nextInt();
                            LocalDate dateOfOrder = LocalDate.of(year, month, day);
                            while (!dateOfOrder.isAfter(LocalDate.now()) || listaDat.contains(dateOfOrder)) {
                                System.out.println("Podaj rok");
                                year = scanner.nextInt();
                                System.out.println("Podaj miesiąc");
                                month = scanner.nextInt();
                                System.out.println("Podaj dzień");
                                day = scanner.nextInt();
                                dateOfOrder = LocalDate.of(year, month, day);
                            }
                            listaDat.add(dateOfOrder);
                            Zamowienie zamowienie = new Zamowienie(dateOfOrder, kwiatWDoniczce);
                            Zamowienie.zapisZamowienia(zamowienie);
                        } else {
                            System.out.println("Nie wystarczająca ilość kwiatów w doniczce proszę czekąć na dostawe w dniu " + LocalDate.now().plusDays(3 + counter));
                            LocalDate delay = LocalDate.now().plusDays(3 + counter);
                            kwiatWDoniczceOpozniony = new KwiatWDoniczce(0, 0, 0, storczyk);
                            delayDate.add(delay);
                            Zamowienie zamowienie = new Zamowienie(LocalDate.now().plusDays(3), kwiatWDoniczceOpozniony);
                            Zamowienie.zapisZamowienia(zamowienie);
                            if (kwiatWDoniczceOpozniony.dostepnosc()) {
                                KwiatWDoniczce.odejmijZMagazynu(0, 0, 0, kwiatWDoniczceOpozniony.getIloscStorczykow());
                            }
                            counter += 2;
                        }


                    case "3":

                        for (LocalDate localDate : listaDat) {
                            if (dateNow.plusDays(i).equals(localDate)) {
                                System.out.println("Zamówienie z dnia " + localDate + "gotowe do odbioru\n\n");
                            } else if (dateNow.plusDays(i).isAfter(localDate)) {
                                System.out.println("Zamówienie" + localDate + " wykonane\n\n");
                            } else {
                                System.out.println("Zamówienie zdnia" + localDate + "w trakcie realizacji\n\n");
                            }
                        }

                    case "4":

                        if ((dateNow.plusDays(i).getDayOfMonth() - dateNow.getDayOfMonth()) % 2 == 0) {
                            Dostawa.read();
                        }
                        i++;

                        break;
                    case "5":
                        i = 365;
                        break;
                    default:
                        System.out.println("Nie ma takiej opcji");
                        break;
                }

            } catch (DateTimeException e) {
                System.out.println("Podano niedozowloną datę !!!");
            } catch (InputMismatchException e) {
                System.out.println("Zła wartość !!!");
            }
        }
    }
}

