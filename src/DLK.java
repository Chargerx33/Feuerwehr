/**
 * Klasse, die eine Drehleiter mit Korb (DLK) repräsentiert und von der Klasse Fahrzeug erbt.
 * die Klasse hat zusätzlich das Attribut hoehe, welches die maximale Einsatzhöhe der DLK angibt
 */
public class DLK/*Drehleiter (mit Korb)*/ extends Fahrzeug {
    private int hoehe;

    /**
     * Konstruktor für die DLK-Klasse.
     *
     * @param fahrzeugnummer Die Nummer der Drehleiter.
     * @param hoehe Die Höhe der Drehleiter mit Korb.
     */
    public DLK(int fahrzeugnummer, int hoehe) {
        super(fahrzeugnummer, Fahrzeugkategorie.DLK,FahrzeugArt.LKW,2);
        this.hoehe = hoehe;

    }
    /**
     * getter für das Attribut hoehe.
     *
     * @return gibt die Höhe der Drehleiter mit Korb zurück.
     */
    public int getHoehe() {
        return hoehe;
    }
}
