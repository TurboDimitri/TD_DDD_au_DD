import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;


public class VenueRepo{
    static final int NB_WEEKS = 4;
    HashMap<String,Venue> data;
    File saveFile;
    private ObjectInputStream iS;
    private ObjectOutputStream oS;

    public VenueRepo(String fileName,boolean ecriture){
        if(ecriture){
            this.saveFile = new File(fileName);
            data = new HashMap<String,Venue>();
        }
        else{
            FileInputStream finStream;
            try {
                finStream = new FileInputStream(fileName);
                iS = new ObjectInputStream(finStream);
                this.data = (HashMap<String, Venue>) iS.readObject();
                iS.close();
                finStream.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    public Venue findVenueByID(String ID){
        FileInputStream finStream;
        try {
            finStream = new FileInputStream(saveFile);
            iS = new ObjectInputStream(finStream);
            data = (HashMap<String,Venue>) iS.readObject();
            iS.close();
            finStream.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return data.get(ID);
        

    }
    public ArrayList<Venue> findbVenueByEventName(String ref){
        ArrayList<Venue> Venues = new ArrayList<>();
        for( Venue i : data.values()){
            for(Event j : i.getEvents()){
                if (j.getName().equals(ref)){
                    Venues.add(i);
                }
            }
        }return Venues;
    }

    public void save(Venue Venue){
        try {
            FileOutputStream fooStream = new FileOutputStream(saveFile);
            oS = new ObjectOutputStream(fooStream);
            data.put(Venue.getName(), Venue);
            fooStream.write("".getBytes());
            oS.writeObject(data);
            oS.close();
            fooStream.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public boolean isWeekDay(LocalDate date){
        if(date.getDayOfWeek()==DayOfWeek.MONDAY || date.getDayOfWeek()==DayOfWeek.TUESDAY || date.getDayOfWeek()==DayOfWeek.WEDNESDAY || date.getDayOfWeek()==DayOfWeek.THURSDAY || date.getDayOfWeek()==DayOfWeek.FRIDAY ){
            return true;
        }
        return false;
    }

    public boolean VerifOneEmptyVenue(){
        boolean res = false;
        for(Venue venue : data.values()){
            if (venue.events.isEmpty() && !res){
                res = true;
            }
            if (venue.events.isEmpty() && res){
                return false;
            }
        }
        return res;
    }

    public boolean VerifWeekendPriority(){
        boolean[] marks =new boolean[53];
        for(Venue venue : data.values()){
            for(String date : venue.dispo.keySet()){
                LocalDate localDate = LocalDate.parse(date);
                int week = localDate.get(WeekFields.of(Locale.FRANCE).weekOfYear());
                if (venue.dispo.get(date).booked){
                    if (isWeekDay(localDate)){
                        marks[week] = true;
                    }
                }
            }
            for(String date : venue.dispo.keySet()){
                LocalDate localDate = LocalDate.parse(date);
                int week = localDate.get(WeekFields.of(Locale.FRANCE).weekOfYear());
                if (!isWeekDay(localDate) && !venue.dispo.get(date).booked && marks[week]){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean VerifAtleastOneCOncertPerweekIfPossible(){
        int libertyDeg = data.size()*NB_WEEKS - nbConcert();
        for(Venue venue : data.values()){
            boolean[] marks =new boolean[53];
            for(Event event : venue.events){
                if(event instanceof Concert){
                    LocalDate date = event.getDate();
                    int week = date.get(WeekFields.of(Locale.FRANCE).weekOfYear());
                    marks[week]=true;
                }
            }
            for(int i : venue.weeks){
                if(!marks[i]){
                    if(libertyDeg<0){
                        return false;
                    }
                    libertyDeg--;
                }
            }
        }
        return true;
    }
    public boolean Verif(){
        return VerifAtleastOneCOncertPerweekIfPossible() && VerifOneEmptyVenue() && VerifWeekendPriority();
    }
    public int nbConcert(){
        int s =0;
        for(Venue venue : data.values()){
            for(Event event : venue.events){
                if(event instanceof Concert){
                    s+=1;
                }
            }
        }
        return s;
    }
    public void update(Venue Venue){
        data.put(Venue.getName(), Venue);
    }

}