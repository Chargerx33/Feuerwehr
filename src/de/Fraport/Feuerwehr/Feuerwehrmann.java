package de.Fraport.Feuerwehr;

import de.Fraport.Feuerwehr.Enumerations.Dienstgrad;

import java.util.Random;

/**
 * Klasse, die einen Feuerwehrmann repräsentiert.<p>
 * Die Attribute geben ihre jeweilige bezeichnung an
 */
public abstract class Feuerwehrmann {
    private int personalnummer;

    private Dienstgrad dienstgrad;
    /**
     * Konstruktor für die Feuerwehrmann-Klasse.<p>
     * Der Dienstgrad wird mangels Informationen zufällig generiert
     *
     * @param personalnummer Die Personalnummer des Feuerwehrmanns.
     */
    public Feuerwehrmann(int personalnummer){
        Random random = new Random();
        Dienstgrad[] moeglicheDienstgrade = {Dienstgrad.A_DIENST,Dienstgrad.B_DIENST,Dienstgrad.C_DIENST,Dienstgrad.D_DIENST};
        this.personalnummer = personalnummer;
        this.dienstgrad = moeglicheDienstgrade[random.nextInt(4)];
    }

    /**
     * Gibt die Personalnummer des Feuerwehrmanns zurück.
     *
     * @return Die Personalnummer des Feuerwehrmanns.
     */
    public int getPersonalnummer() {
        return personalnummer;
    }

    /**
     * Gibt den Dienstgrad des Feuerwehrmanns zurück.
     *
     * @return Der Dienstgrad des Feuerwehrmanns.
     */
    public Dienstgrad getDienstgrad() {
        return dienstgrad;
    }
}

/**
 * Klasse, die einen Feuerwehrmann mit der Spezialisierung als LKW-Fahrer repräsentiert.
 */
class LkwFahrer extends Feuerwehrmann{

    /**
     * Konstruktor für die LkwFahrer-Klasse.
     *
     * @param personalnummer Die Personalnummer des LKW-Fahrers.
     */
    public LkwFahrer(int personalnummer) {
        super(personalnummer);
    }
}

/**
 * Klasse, die einen Feuerwehrmann repräsentiert, der nur als PKW-Fahrer tätig ist.
 */
class PkwFahrer extends Feuerwehrmann{

    /**
     * Konstruktor für die PkwFahrer-Klasse.
     *
     * @param personalnummer Die Personalnummer des Feuerwehrmanns.
     */
    public PkwFahrer(int personalnummer) {
        super(personalnummer);
    }
}
