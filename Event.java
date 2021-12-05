import java.time.LocalDate;

public abstract class Event {
    // int beginHour;
    // int endHour;
    int capacity;
    String name;

    public String getName() {
        return name;
    }
    public String getStringDate(LocalDate date){
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    }    
   
}