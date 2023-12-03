package de.Fraport.Feuerwehr;

/**
 * Klasse, die einen Einsatzleitwagen (ELW) repräsentiert und von der Klasse Fahrzeug erbt.
 * die Klasse hat zusätzlich das Attribut dienstgrad, welches im Einsatz den höchsten Dienstgrad der auf dem Fahrzeug befindlichen Feuerwehrleute angibt
 *
 */
public class ELW/*Einsatzleitwagen*/ extends Fahrzeug {

    private Dienstgrad dienstgrad;

    /**
     * Konstruktor für die ELW-Klasse.
     *
     * @param fahrzeugnummer Die Nummer des ELWs.
     */
    public ELW(int fahrzeugnummer) {
        super(fahrzeugnummer, Fahrzeugkategorie.ELW, FahrzeugArt.PKW, 2);

    }

    /**
     * Gibt den Dienstgrad des höchstrangigen Feuerwehrmanns auf dem ELW zurück
     *
     * @return Der Dienstgrad höchstrangigen Feuerwehrmanns
     */
    public Dienstgrad getDienstgrad() {
        return dienstgrad;
    }
    /**
    * Setzt den Dienstgrad des höchstrangigen auf dem ELW befindlichen Feuerwehrmanns.
    *
    * @param dienstgrad Der zu setzende Dienstgrad.
    */
    public void setDienstgrad(Dienstgrad dienstgrad) {
        this.dienstgrad = dienstgrad;
    }
}
