import java.time.LocalDate;


public class Concert extends Event{
    LocalDate date;


    public Concert(LocalDate date, String name, int capacity) {
        this.date = date;
        this.name = name;
        this.capacity = capacity;
    }
}