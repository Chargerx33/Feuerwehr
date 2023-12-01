import java.util.ArrayList;

public class MTF/*Mannschaftstransportfahrzeug*/ extends Fahrzeug {
    private int baujahr;

    public MTF(int fahrzeugnummer,Status status,int baujahr) {
        super(fahrzeugnummer,FahrzeugKategorie.MTF, status, FahrzeugArt.PKW, 14);
        this.baujahr = baujahr;
    }

    public int getBaujahr() {
        return baujahr;
    }
}
