import java.util.Random;

/**
 * Klasse, die einen Feuerwehrmann repräsentiert.
 * Die Attribute geben ihre jeweilige bezeichnung an
 */
public class Feuerwehrmann {
    private int personalnummer;

    private Dienstgrad dienstgrad;
    /**
     * Konstruktor für die Feuerwehrmann-Klasse.
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
