package de.Fraport.Feuerwehr;

import de.Fraport.Feuerwehr.Enumerations.Dienstgrad;
import de.Fraport.Feuerwehr.Enumerations.Fahrzeugkategorie;

import java.util.ArrayList;
import java.util.Random;

/**
 * Meistens Klasse, die ein allgemeines Fahrzeug repräsentiert.
 * Die Klasse stellt die folgenden Attribute bereit:
 * fahrzeugnummer ist die Nummer des Fahrzeugs
 * fahrzeugKategorie gibt an welcher Kategorie ein Fahrzeug zuzuordnen ist
 * art gibt an, welche Art von Qualifikation man für das Fahrzeug benötigt (z.B. PKW-Führerschein)
 * sitze gibt an, wie viele Sitze das Fahrzeug hat
 */
public class Fahrzeug {

    protected int fahrzeugnummer;
    protected Fahrzeugkategorie fahrzeugKategorie;
    protected FahrzeugArt art;
    protected int sitze;

    private ArrayList<Feuerwehrmann> besatzung = new ArrayList<Feuerwehrmann>(sitze);

    /**
     * Standardkonstruktor für ein Fahrzeug. Setzt die fahrzeugnummer uf -1, Sitzanzahl auf 0 und den Typ auf UNDEFINED.
     */
    public Fahrzeug() {
        this.fahrzeugnummer = -1;
        this.sitze = 0;
        this.art = FahrzeugArt.UNDEFINED;
    }

    /**
     * Konstruktor für ein Fahrzeug mit spezifischen Parametern.
     *
     * @param fahrzeugnummer Die Nummer des Fahrzeugs.
     * @param fahrzeugKategorie Die Kategorie des Fahrzeugs.
     * @param art Die Art des Fahrzeugs.
     * @param sitze Die Anzahl der Sitze im Fahrzeug.
     */
    public Fahrzeug(int fahrzeugnummer, Fahrzeugkategorie fahrzeugKategorie, FahrzeugArt art, int sitze) {
        this.fahrzeugnummer = fahrzeugnummer;
        this.fahrzeugKategorie = fahrzeugKategorie;
        this.art = art;
        this.sitze = sitze;

    }

    /**
     * Gibt die Anzahl der Sitze des Fahrzeugs zurück.
     *
     * @return Die Anzahl der Sitze des Fahrzeugs.
     */
    public int getSitze(){
        return sitze;
    }

    /**
     * Gibt die Art des Fahrzeugs zurück.
     *
     * @return Die Art des Fahrzeugs.
     */
    public FahrzeugArt getArt() {
        return art;
    }

    /**
     * Gibt die Fahrzeugnummer zurück.
     *
     * @return Die Fahrzeugnummer.
     */
    public Integer getFahrzeugnummer(){
        return fahrzeugnummer;
    }

    /**
     * Gibt die FahrzeugKategorie zurück
     * @return Die FahrzeugKategorie
     */
    public Fahrzeugkategorie getFahrzeugKategorie(){
        return (Fahrzeugkategorie) fahrzeugKategorie;
    }

    /**
     * Setzt die Besatzung auf das Fahrzeug.
     *
     * @param besatzung Die Besatzung des Fahrzeugs.
     */
    public void aufsitzen(ArrayList<Feuerwehrmann> besatzung) {
        this.besatzung = besatzung;
    }

    /**
     * Entfernt die Besatzung vom Fahrzeug und gibt diese zurück.
     *
     * @return Die entfernte Besatzung des Fahrzeugs
     */
    public ArrayList<Feuerwehrmann> absitzen() {
        ArrayList<Feuerwehrmann> rueckfahrendeBesatzung = new ArrayList<Feuerwehrmann>();
        rueckfahrendeBesatzung.addAll(besatzung);
        besatzung.clear();
        return rueckfahrendeBesatzung;
    }

    /**
     * Enumeration, die die möglichen Fahrzeugarten repräsentiert.
     * UNDEFINED gibt einen Fehler oder Abbruch an.
     */
    enum FahrzeugArt {
        PKW, LKW, UNDEFINED
    }

}
/**
 * Klasse, die einen Einsatzleitwagen (ELW) repräsentiert und von der Klasse Fahrzeug erbt.
 * die Klasse hat zusätzlich das Attribut dienstgrad, welches im Einsatz den höchsten Dienstgrad der auf dem Fahrzeug befindlichen Feuerwehrleute angibt
 *
 */
class ELW/*Einsatzleitwagen*/ extends Fahrzeug {

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
/**
 * Klasse, die ein Tanklöschfahrzeug repräsentiert und von der Klasse Fahrzeug erbt.
 */
class TLF/*Tankloeschfahrzeug*/ extends Fahrzeug{
    /**
     * Der maximale Tankinhalt des Tanklöschfahrzeugs.
     */
    private int tank;

    /**
     * Konstruktor für die TLF-Klasse.
     * Der Tankinhalt wird aus einer Auswahl von zwei Tankgrößen zufällig gewählt, die Tankgrößen kommen von realen Fahrzeugen der Feuerwehr Frankfurt und stellen Liter dar
     * @param fahrzeugnummer Die Fahrzeugnummer des Tanklöschfahrzeugs.
     */
    public TLF(int fahrzeugnummer) {
        super(fahrzeugnummer, Fahrzeugkategorie.TLF, FahrzeugArt.LKW, 4);
        int[] moeglicheTanks = {3000,11000};
        Random rand = new Random();
        this.tank = moeglicheTanks[rand.nextInt(moeglicheTanks.length)];
    }

    /**
     * Gibt den Tankinhalt des Tanklöschfahrzeugs zurück.
     *
     * @return Der Tankinhalt.
     */
    public int getTank() {
        return tank;
    }

}

/**
 * Klasse, die eine Drehleiter mit Korb (DLK) repräsentiert und von der Klasse Fahrzeug erbt.
 * die Klasse hat zusätzlich das Attribut hoehe, welches die maximale Einsatzhöhe der DLK angibt
 */
class DLK/*Drehleiter (mit Korb)*/ extends Fahrzeug {
    private int hoehe;

    /**
     * Konstruktor für die DLK-Klasse.
     *
     * @param fahrzeugnummer Die Nummer der Drehleiter.
     * @param hoehe Die Höhe der Drehleiter mit Korb.
     */
    public DLK(int fahrzeugnummer, int hoehe) {
        super(fahrzeugnummer, Fahrzeugkategorie.DLK, FahrzeugArt.LKW,2);
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

/**
 * Klasse, die ein Mannschaftstransportfahrzeug (MTF) repräsentiert und von der Klasse Fahrzeug erbt.
 * die Klasse hat zusätzlich das Attribut baujahr, welches das Baujahr des MTFs angibt
 */
class MTF/*Mannschaftstransportfahrzeug*/ extends Fahrzeug {
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
