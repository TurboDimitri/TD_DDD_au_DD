
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Venue implements Serializable {
    String name;
    int capacity;
    ArrayList<Event> events = new ArrayList<>();
    HashMap<String, TupleHour> dispo = new HashMap<>();

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
            if (!dispo.containsKey(date) && !dispo.containsKey(date2)) {
                dispo.put(date, tupleFirstDay);
                dispo.put(date2, tupleSecondDay);
            } else {
                throw new IllegalArgumentException(
                        "Au moins une des deux dates est prise mon reuf................... arrête la picole putain !");
            }
        } else {
            if (!dispo.containsKey(date)) {
                dispo.put(date, new TupleHour(start, end));
            } else {
                throw new IllegalArgumentException(
                        "La date est prise mon reufton tu devrais considérer d'arrêter de boire non ?");
            }
        }

    }

    public void addTheatre(Theater theater){
        LocalDate dateBegin = theater.getDate();
        LocalDate dateEnding = theater.getEndDate();
        while (dateBegin.isBefore(dateEnding)){
            if (dispo.get(theater.getStringDate(dateBegin)).booked==true){
                throw new IllegalArgumentException(
                        "La date est prise mon reufton tu devrais considérer d'arrêter de boire non ?");
            }else{
                dispo.get(theater.getStringDate(dateBegin)).booked=true;
                events.add(theater);
                dateBegin.plusDays(1);
            }
        }
    }

    public void addConcert(Concert concert){
        if (dispo.get(concert.getStringDate(concert.getDate())).booked==true){
            throw new IllegalArgumentException(
                    "La date est prise mon reufton tu devrais considérer d'arrêter de boire non ?");
        }else{
            dispo.get(concert.getStringDate(concert.getDate())).booked=true;
                events.add(concert);
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
    }
}
