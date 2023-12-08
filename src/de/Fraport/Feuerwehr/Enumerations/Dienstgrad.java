package de.Fraport.Feuerwehr.Enumerations;

/**
 * Die Enumeration "Dienstgrad" gibt den Dienstgrad eines Feuerwehrmanns oder ELWs an.<p>
 * Dabei ist A_DIENST der höchste und D_DIENST der niedrigste Dienstgrad.
 */
public enum Dienstgrad {
    A_DIENST(3),
    B_DIENST(2),
    C_DIENST(1),
    D_DIENST(0);

    private Integer rang;

    /**
     * Konstruktor der den Rang für einen Dienstgrad setzt
     *
     * @param rang Der Rang des Dienstgrades.
     */
    Dienstgrad(int rang) {
        this.rang = rang;
    }

    /**
     * Überprüft, ob der aktuelle Dienstgrad höher ist als ein anderer Dienstgrad.
     *
     * @param other Der zu vergleichende Dienstgrad.
     * @return {@code true}, wenn der aktuelle Dienstgrad höher ist als der andere; {@code false} wenn der aktuelle Dienstgrad niedriger ist als der andere.
     */
    public boolean istHoeherAls(Dienstgrad other) {
        return this.rang > other.rang;
    }
}
