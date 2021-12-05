
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Venue implements Serializable {
    String name;
    int capacity;
    ArrayList<Event> events = new ArrayList<>();
    HashMap<String, TupleHour> hashtest = new HashMap<>();

    public Venue(int capacity, String name) {
        this.capacity = capacity;
        this.name = name;
    }

    public ArrayList<Event> getEvents() {
        return this.events;
    }

    public String getName() {
        return this.name;
    }

    public void addOpenHour(int year, int month, int dayOfMonth, int start, int end) {
        String date = dayOfMonth + "/" + month + "/" + year;
        if (start > 24 || start < 0 || end > 24 || end < 0) {
            throw new IllegalArgumentException("Les heures sont pas bonnes mon reuf, arrête la picole.");
        }
        if (end < start) {
            LocalDate dateFirstDay = LocalDate.of(year, month, dayOfMonth);
            LocalDate dateSecondDay = LocalDate.ofYearDay(year, dateFirstDay.getDayOfYear() + 1);
            // On ajoute une journée au premier calendrier si l'event fini apres minuit
            String date2 = dateSecondDay.getDayOfMonth() + "/" + dateSecondDay.getMonthValue() + "/"
                    + dateSecondDay.getYear();
            TupleHour tupleFirstDay = new TupleHour(start, 24);
            TupleHour tupleSecondDay = new TupleHour(0, end);
            if (!hashtest.containsKey(date) && !hashtest.containsKey(date2)) {
                hashtest.put(date, tupleFirstDay);
                hashtest.put(date2, tupleSecondDay);
            } else {
                throw new IllegalArgumentException(
                        "Au moins une des deux dates est prise mon reuf................... arrête la picole putain !");
            }
        } else {
            if (!hashtest.containsKey(date)) {
                hashtest.put(date, new TupleHour(start, end));
            } else {
                throw new IllegalArgumentException(
                        "La date est prise mon reufton tu devrais considérer d'arrêter de boire non ?");
            }
        }

    }

}

class TupleHour implements Serializable {
    int beginHour;
    int endHour;
    boolean booked = false;

    TupleHour(int beginHour, int endHour) {
        if (beginHour > 24 || beginHour < 0 || endHour > 24 || endHour < 0) {
            throw new IllegalArgumentException("Les heures sont pas bonnes mon reuf, arrête la picole.");
        }
        this.beginHour = beginHour;
        this.endHour = endHour;
        booked = true;
    }
}
