package de.Fraport.Feuerwehr;

import de.Fraport.Feuerwehr.Enumerations.FahrzeugArt;
import de.Fraport.Feuerwehr.Enumerations.Fahrzeugkategorie;

import java.util.ArrayList;
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
        return fahrzeugKategorie;
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

}