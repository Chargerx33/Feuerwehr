import java.util.ArrayList;

public class DLK/*Drehleiter (mit Korb)*/ extends Fahrzeug {
    private int hoehe;
    public DLK(int fahrzeugnummer, int hoehe) {
        super(fahrzeugnummer,FahrzeugKategorie.DLK,FahrzeugArt.LKW,2);
        this.hoehe = hoehe;

    }

    public int getHoehe() {
        return hoehe;
    }
}
