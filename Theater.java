import java.time.LocalDate;

public class Theater extends Event {
    LocalDate dateBegin;
    LocalDate dateEnding;

    public Theater(LocalDate dateBegin, LocalDate dateEnding, String name, int capacity) {
        this.dateBegin = dateBegin;
        this.dateEnding = dateEnding;
        this.name = name;
        this.capacity = capacity;
    }
    public LocalDate getDate(){
        return dateBegin;
    }
    public LocalDate getEndDate(){
        return dateEnding;
    }
}