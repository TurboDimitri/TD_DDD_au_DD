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
}