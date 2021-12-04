import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class VenueRepo{
    HashMap<String,Venue> data;
    File saveFile;
    private ObjectInputStream iS;
    private ObjectOutputStream oS;

    public VenueRepo(String fileName){
        this.saveFile = new File(fileName);
        data = new HashMap<String,Venue>();

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
    public void update(Venue Venue){
        data.put(Venue.getName(), Venue);
    }

}