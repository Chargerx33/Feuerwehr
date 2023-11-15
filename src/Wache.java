import java.util.ArrayList;

public class Wache {
    public ArrayList<Fahrzeug> fahrzeughalle = new ArrayList<Fahrzeug>();
    public ArrayList<Feuerwehrmann> personal = new ArrayList<Feuerwehrmann>();
    public void addFahrzeug(Fahrzeug fahrzeug) {
        fahrzeughalle.add(fahrzeug);
    }
    public void addPersonal(Feuerwehrmann feuerwehrmann){
        personal.add(feuerwehrmann);
    }



}
