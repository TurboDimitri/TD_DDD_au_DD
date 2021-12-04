import java.time.LocalDate;
import java.util.Calendar;

public class Theater extends Event{
    LocalDate dateBegin;
    LocalDate dateEnding;
    String name;


    public Theater(LocalDate dateBegin, LocalDate dateEnding, String name, int capacity) {
        this.dateBegin = dateBegin;
        this.dateEnding = dateEnding;
        this.name = name;
        this.capacity = capacity;
    }

}