package de.Fraport.Feuerwehr;

import de.Fraport.Feuerwehr.Enumerations.Einsatzart;

import java.util.ArrayList;

/**
 * Die Klasse repräsentiert einen Einsatz.
 * einsatzNummer ist die Einsatznummer des Einsatzes.
 * einsatzArt ist die Art des Einsatzes.
 * benoetigte Feuerwehrleute gibt die noch benötigten Feuerwehrleute an.
 * benoetigteXXX gibt die Anzahl den noch benötigten Fahrzeuge einer bestimmten art an.
 * fahrzeuge speichert die sich am Einsatz befindlichen Fahrzeuge.
 */
public class Einsatz {
    private int einsatznummer;
    private Einsatzart einsatzArt;
    private int benoetigteFeuerwehrleute;
    private int benoetigteMTF; //Mannschaftstransportfahrzeug (MTF)
    private int benoetigteELW; //Einsatzleitwagen (ELW)
    private int benoetigteTLF; //Tanklöschfahrzeug (TLF)
    private int benoetigteDLK; //Drehleiter mit Korb (DLK)
    private ArrayList<Fahrzeug> fahrzeuge = new ArrayList<Fahrzeug>();

    /**
     * Konstruktor für die Einsatz-Klasse.
     * Die benötigten Feuerwehrleute und Einsatzfahrzeuge werden abhängig von der Einsatzart über "setEinsatz" gesetzt.
     *
     * @param einsatznummer Die Nummer des Einsatzes.
     * @param einsatzart Die Art des Einsatzes.
     */
    public Einsatz(int einsatznummer, Einsatzart einsatzart)
    {
        this.einsatznummer = einsatznummer;
        this.einsatzArt = einsatzart;

        switch (einsatzArt) {
            case WOHNUNGSBRAND -> setEinsatz(22, 1, 2, 1, 1);
            case VERKEHRSUNFALL -> setEinsatz(16, 1, 1, 1, 0);
            case NATURKATASTROPHE -> setEinsatz(55, 3, 3, 3, 2);
            case INDUSTRIEUNFALL -> setEinsatz(40, 3, 2, 2, 2);
        }
    }

    /**
     *
     * Setzt die Atribute des Einsatzes (ausgenommen fahrzeuge)
     */
    private void setEinsatz(int benoetigteFeuerwehrleute, int benoetigteElw, int benoetigteTlf, int benoetigteMtf, int benoetigteDlk) {
        this.benoetigteFeuerwehrleute = benoetigteFeuerwehrleute;
        this.benoetigteELW = benoetigteElw;
        this.benoetigteTLF = benoetigteTlf;
        this.benoetigteDLK = benoetigteDlk;
        this.benoetigteMTF = benoetigteMtf;
    }

    /**
     * Getter für die Einsatznummer
     *
     * @return  gibt die Einsatznummer des Einsatzes zurück
     */
    public int getEinsatznummer(){
        return einsatznummer;
    }

    /**
     * Getter für die noch benötigten Feuerwehrleute
     * @return gibt die noch benötigten Feuerwehrleute zurück
     */
    public int getBenoetigteFeuerwehrleute() {
        return benoetigteFeuerwehrleute;
    }

    /**
     * Reduziert die Anzahl der benötigten Feuerwehrleute um die angegebene Anzahl.
     *
     * @param bediente Die Anzahl der Feuerwehrleute, die dem Einsatz hinzugefügt werden/für den Einsatz reserviert wurden.
     */
    public void bedieneFeuerwehrmann(int bediente){
        benoetigteFeuerwehrleute-=bediente;
    }

    /**
     * Getter für die noch benötigten MTFs
     * @return gibt die noch benötigten MTFs zurück
     */
    public int getBenoetigteMTF() {
        return benoetigteMTF;
    }

    /**
     * Reduziert die Anzahl der benötigten MTFs um die angegebene Anzahl.
     *
     * @param bediente Die Anzahl der MTFs, die dem Einsatz hinzugefügt werden/für den Einsatz reserviert wurden.
     */
    public void bedieneMTF(int bediente){
        benoetigteMTF -=bediente;
    }

    /**
     * Getter für die noch benötigten ELWs
     * @return gibt die noch benötigten ELWs zurück
     */
    public int getBenoetigteELW() {
        return benoetigteELW;
    }

    /**
     * Reduziert die Anzahl der benötigten ELWs um die angegebene Anzahl.
     *
     * @param bediente Die Anzahl der ELWs, die dem Einsatz hinzugefügt werden/für den Einsatz reserviert wurden.
     */
    public void bedieneElw(int bediente){
        benoetigteELW -=bediente;
    }

    /**
     * Getter für die noch benötigten TLFs
     * @return gibt die noch benötigten TLFs zurück
     */
    public int getBenoetigteTLF() {
        return benoetigteTLF;
    }

    /**
     * Reduziert die Anzahl der benötigten TLFs um die angegebene Anzahl.
     *
     * @param bediente Die Anzahl der TLFs, die dem Einsatz hinzugefügt werden/für den Einsatz reserviert wurden.
     */
    public void bedieneTlf(int bediente){
        benoetigteTLF -=bediente;
    }

    /**
     * Getter für die noch benötigten DLKs
     * @return gibt die noch benötigten DLKs zurück
     */
    public int getBenoetigteDLK() {
        return benoetigteDLK;
    }
    /**
     * Reduziert die Anzahl der benötigten DLKs um die angegebene Anzahl.
     *
     * @param bediente Die Anzahl der DLKs, die dem Einsatz hinzugefügt werden/für den Einsatz reserviert wurden.
     */
    public void bedieneDlk(int bediente){
        benoetigteDLK -=bediente;
    }

    /**
     * Getter für die Einsatzart des Einsatzes
     * @return gibt die Art des Einsatzes
     */
    public Einsatzart getEinsatzArt() {
        return einsatzArt;
    }

    /**
     * Erstellt eine ArrayList vom typ String der Sonderattribute der am Einsatz befindlichen Fahrzeuge
     *
     * @return gibt die Liste der Sonderattribute zurück
     */
    public String getSonderattribute(){
        StringBuilder sonderattribute = new StringBuilder();
        sonderattribute.append(einsatzArt.toString() + "\n");
        int i = 1;
        for (Fahrzeug f: fahrzeuge) {
            sonderattribute.append(String.valueOf(i)+": ");
            i++;
            if (f instanceof ELW) {
                switch (((ELW) f).getDienstgrad()){
                    case A_DIENST -> {
                        sonderattribute.append("ELW: A-Dienst\n");
                        break;
                    }
                    case B_DIENST -> {
                        sonderattribute.append("ELW: B-Dienst\n");
                        break;
                    }
                    case C_DIENST -> {
                        sonderattribute.append("ELW: C-Dienst\n");
                        break;
                    }
                    case D_DIENST -> {
                        sonderattribute.append("ELW: D-Dienst\n");
                        break;
                    }

                }
            }
            else if (f instanceof TLF) {
                sonderattribute.append("TLF: " + String.valueOf(((TLF) f).getTank()) + " Liter\n");
            }
            else if (f instanceof MTF) {
                sonderattribute.append("MTF: Baujahr: " + String.valueOf(((MTF) f).getBaujahr()) + "\n");
            }
            else if (f instanceof DLK) {
                sonderattribute.append("DLK: Anleiterhöhe: " + String.valueOf(((DLK) f).getHoehe()) + " Meter\n");
            }
        }
        return sonderattribute.toString();
    }
    /**
     * Fügt ein Fahrzeug zum Einsatz hinzu.
     *
     * @param fahrzeug Das hinzuzufügende Fahrzeug.
     */
    public void fahrzeugeEinbeziehen(Fahrzeug fahrzeug){
        fahrzeuge.add(fahrzeug);
    }

    /**
     * Gibt die Fahrzeuge für die Rückfahrt zurück um den Einsatz folgend beenden zu können.
     *
     * @return gibt die Fahrzeuge für die Rückfahrt zurück.
     */
    public ArrayList<Fahrzeug> einsatzEnde(){
        ArrayList<Fahrzeug> rueckfahrt = new ArrayList<Fahrzeug>(fahrzeuge);
        fahrzeuge.clear();
        return rueckfahrt;
    }
}
