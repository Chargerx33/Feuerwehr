import java.util.ArrayList;

public class DLK extends Fahrzeug {
    private int hoehe;
    public DLK(int fahrzeugnummer,Status status, int hoehe) {
        super(fahrzeugnummer,status,FahrzeugArt.LKW,2);
        this.hoehe = hoehe;

    }

}
