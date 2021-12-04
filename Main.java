import java.util.Calendar;
import java.util.HashMap;

public class Main{
    public static void main(String[] args){
        // Calendar test = Calendar.getInstance();
        // test.set(2021, 12, 2, 23, 35);
        // Calendar cal2 = Calendar.getInstance();
        // cal2.set(2021, 12, 2, 23, 35);
        // HashMap<Calendar,String> hashtest = new HashMap<>();
        // hashtest.put(test, "pouet pouet");

        // System.out.println(hashtest.get(test) + "\n" + hashtest.get(cal2));
        Venue Salle1 = new Venue(50);
        Salle1.init(2022, 1, 1, 14, 18);
        Salle1.init(2022, 1, 2, 22, 2);
        Salle1.init(2022, 1, 3, 14, 18);
        System.out.println("");
        
    }
}