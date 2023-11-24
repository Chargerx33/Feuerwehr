import java.util.ArrayList;

public class DLK/*Drehleiter (mit Korb)*/ extends Fahrzeug {
    private int hoehe;
    public DLK(int fahrzeugnummer,Status status, int hoehe) {
        super(fahrzeugnummer,FahrzeugKategorie.DLK,status,FahrzeugArt.LKW,2);
        this.hoehe = hoehe;

    }

}
