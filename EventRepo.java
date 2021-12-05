import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashMap;

public class EventRepo {
    HashMap<String, Event> data;
    File saveFile;
    private ObjectInputStream iS;
    private ObjectOutputStream oS;

    public EventRepo(String fileName) {
        this.saveFile = new File(fileName);
        data = new HashMap<String, Event>();

    }

    public Event findEventByID(String ID) {
        FileInputStream finStream;
        try {
            finStream = new FileInputStream(saveFile);
            iS = new ObjectInputStream(finStream);
            data = (HashMap<String, Event>) iS.readObject();
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

    public void save(Event Event) {
        try {
            FileOutputStream fooStream = new FileOutputStream(saveFile);
            oS = new ObjectOutputStream(fooStream);
            data.put(Event.getName(), Event);
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

    public void update(Event Event) {
        data.put(Event.getName(), Event);
    }

    public void addEvent(Concert concert, Venue venue) {
        if(venue.hashtest.containsKey(concert.getName()) && venue.hashtest.get(concert.getName()).booked){
            throw new IllegalArgumentException("Un événement est déjà booké mon reufton aha t'es bargeot ou quoi ??");
        }
        else{
            venue.events.add(concert);
            venue.hashtest.put(concert.getName(), new TupleHour(concert.beginHour, concert.endHour));
        }
    }

    public void addEvent(Theater theater, Venue venue) {
        if (venue.hashtest.containsKey(theater.getName()) && venue.hashtest.get(theater.getName()).booked) {
            throw new IllegalArgumentException("Un événement est déjà booké mon reufton aha t'es bargeot ou quoi ??");
        }
        else{
            venue.events.add(theater);
            LocalDate save = theater.dateBegin;
            while(save.isBefore(theater.dateEnding)){
                venue.hashtest.put(theater.getName(), new TupleHour(theater.beginHour, theater.endHour));
                save.plusDays(1);
            }
        }

    }

}