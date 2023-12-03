package de.Fraport.Feuerwehr;

import de.Fraport.Feuerwehr.Enumerations.FahrzeugArt;
import de.Fraport.Feuerwehr.Enumerations.Fahrzeugkategorie;

import java.util.Random;

/**
 * Klasse, die ein Tanklöschfahrzeug repräsentiert und von der Klasse Fahrzeug erbt.
 */
public class TLF/*Tankloeschfahrzeug*/ extends Fahrzeug{
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
