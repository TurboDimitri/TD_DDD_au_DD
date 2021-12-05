import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;

public class Main {
    // Pour l'instant le main sors juste les Venue et leurs noms grace au repo que
    // j'ai créé une fois grace à createvenuerepo
    public static void main(String[] args) {

        // createAVenueRepo();
        ObjectInputStream iS;
        FileInputStream finStream;
        HashMap<String, Venue> data = new HashMap<>();
        try {
            finStream = new FileInputStream("VenueRepo.txt");
            iS = new ObjectInputStream(finStream);
            data = (HashMap<String, Venue>) iS.readObject();
            iS.close();
            finStream.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (String j : data.keySet()) {
            System.out.println(j);
        }
        System.out.println("");

    }

    // For testing purposes
    public static void createAVenueRepo() {
        Venue Salle1 = new Venue(50, "Salle 1");
        Venue Salle2 = new Venue(150, "Salle 2");
        for (int i = 1; i < 29; i++) {
            LocalDate date = LocalDate.of(2022, 1, i);
            if (i % 2 == 0) {
                Salle1.addOpenHour(2022, 1, i, 14, 18);
            } else {
                Salle1.addOpenHour(2022, 1, i, 18, 24);
            }
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                Salle2.addOpenHour(2022, 1, i, 20, 4);
            } else if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {

            } else {
                Salle2.addOpenHour(2022, 1, i, 20, 23);
            }
        }

        VenueRepo venueRepo = new VenueRepo("VenueRepo.txt");
        venueRepo.update(Salle1);
        venueRepo.save(Salle2);
    }
}