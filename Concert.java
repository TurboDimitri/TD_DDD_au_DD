import java.time.LocalDate;


public class Concert extends Event{
    public LocalDate dateBegin;


    public Concert(LocalDate date, String name, int capacity) {
        this.dateBegin = date;
        this.name = name;
        this.capacity = capacity;
    }

    public LocalDate getDate(){
        return dateBegin;
    }
}