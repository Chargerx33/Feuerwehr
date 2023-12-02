/**
 * Klasse, die ein Mannschaftstransportfahrzeug (MTF) repräsentiert und von der Klasse Fahrzeug erbt.
 * die Klasse hat zusätzlich das Attribut baujahr, welches das Baujahr des MTFs angibt
 */
public class MTF/*Mannschaftstransportfahrzeug*/ extends Fahrzeug {
    /**
     * Das Baujahr des Mannschaftstransportfahrzeugs.
     */
    private int baujahr;

    /**
     * Konstruktor für die MTF-Klasse.
     *
     * @param fahrzeugnummer Die Nummer des Fahrzeugs.
     * @param baujahr        Das Baujahr des Mannschaftstransportfahrzeugs.
     */
    public MTF(int fahrzeugnummer,int baujahr) {
        super(fahrzeugnummer, Fahrzeugkategorie.MTF, FahrzeugArt.PKW, 14);
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
