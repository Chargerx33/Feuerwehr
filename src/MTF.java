import java.util.ArrayList;

public class MTF/*Mannschaftstransportfahrzeug*/ extends Fahrzeug {
    /**
     * Das Baujahr des Mannschaftstransportfahrzeugs.
     */
    private int baujahr;

    public MTF(int fahrzeugnummer,int baujahr) {
        super(fahrzeugnummer,FahrzeugKategorie.MTF, FahrzeugArt.PKW, 14);
        this.baujahr = baujahr;
    }
    /**
     * Gibt das Baujahr des Mannschaftstransportfahrzeugs zurück.
     *
     * @return Das Baujahr des Mannschaftstransportfahrzeugs.
     */
    public int getBaujahr() {
        return baujahr;
    }
}
