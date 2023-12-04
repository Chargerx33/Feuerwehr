package de.Fraport.Feuerwehr;

import de.Fraport.Feuerwehr.Enumerations.Dienstgrad;
import de.Fraport.Feuerwehr.Enumerations.Einsatzart;
import de.Fraport.Feuerwehr.Enumerations.Fahrzeugkategorie;
import de.Fraport.Feuerwehr.UI.EinsatzartPopup;
import de.Fraport.Feuerwehr.UI.FahrzeugPopup;
import de.Fraport.Feuerwehr.UI.NummerAuswahlPopup;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Klasse, die eine Feuerwehrleitstelle repräsentiert.
 * In den Attributen werden die Wache und die Einsätze gespeichert
 */
public class Leitstelle {
    private Wache wache = new Wache();
    private ArrayList<Einsatz> einsaetze = new ArrayList<Einsatz>();

    /**
     * Konstruktor für die Leitstelle welcher die Funktion "generateFeuerwache" aufruft um die Feuerwache zu erstellen
     */
    public Leitstelle() {
        genrateFeuerwache();
    }

    /**
     * Generiert die Feuerwache anhand festgesetzter Werte
     */
    private void genrateFeuerwache() { // Hier werden die gegebenen Daten in die Struktur des Programms eingesetzt
        for (int i = 1; i <= 80; i++) {
            Feuerwehrmann feuerwehrmann;
            if (i <= 10) {
                feuerwehrmann = new LkwFahrer(i);
            } else {
                feuerwehrmann = new PkwFahrer(i);
            }
            wache.newPersonal(feuerwehrmann);
        }
        for (int i = 1; i <= 4; i++) {
            Fahrzeug elw = new ELW(i);
            wache.newFahrzeug(elw);
        }
        for (int i = 1; i <= 5; i++) {
            Fahrzeug tlf = new TLF(i);
            wache.newFahrzeug(tlf);
        }
        for (int i = 1; i <= 4; i++) {
            Fahrzeug mtf = new MTF(i, 2004);
            wache.newFahrzeug(mtf);
        }
        for (int i = 1; i <= 5; i++) {
            Fahrzeug dlk = new DLK(i, 23);
            wache.newFahrzeug(dlk);
        }
    }
    /**
     * Startet die Erfassung eines neuen Einsatzes und bricht diese bei der erzeugung von der Einsatzart "UNDEFINED" ab.
     */
    public void neuerEinsatz() {
        EinsatzartPopup eap = new EinsatzartPopup();
        Einsatzart e = eap.getData();
        Einsatz einsatz = new Einsatz(errechneEinsatzNummer(), e);
        if (e != Einsatzart.UNDEFINED) {

            einsaetze.add(einsatz);
        } else {
            System.out.println("Einsatzaufnahme abgebrochen");
        }
    }

    /**
     * Errechnet die Einsatznummer anhand der bestehenden Einsätze
     * @return gibt dei errechnete Einsatznummer zurück
     */
    private int errechneEinsatzNummer() {
        int highest = 0;
        for (Einsatz e : einsaetze) {
            if (e.getEinsatznummer() > highest) highest = e.getEinsatznummer();
        }
        return (highest + 1);
    }
    /**
     * Gibt die Zeichenkette, welche in "wache.statusDerWache" erzeugt wurde zurück
     *
     * @return die in "wache.statusDerWache" erzeugte Zeichenkette
     */
    public String statusDerWache() {
        return wache.statusDerWache();
    }

    private Einsatz selectEinsatz() {
        ArrayList<Integer> einsatznummern = new ArrayList<Integer>();
        for (Einsatz e : einsaetze) {
            einsatznummern.add(e.getEinsatznummer());
        }
        Collections.sort(einsatznummern);
        NummerAuswahlPopup einsatzAuswahlPopup = new NummerAuswahlPopup();
        int selected = einsatzAuswahlPopup.getData(einsatznummern,"Einsatz auswahl", "Einsatznummer: ");
        for (Einsatz e : einsaetze) {
            if (e.getEinsatznummer() == selected) {
                return e;
            }
        }
        return new Einsatz(-1, Einsatzart.UNDEFINED);
    }
    /**
     * Gibt Informationen zu einem ausgewählten Einsatz zurück.
     * "einsatz" stellt hierbei den zuvor ausgewählten Einsatz dar.
     * @return Informationen welche in "einsatz.getSonderattribute" erzeugt wurden.
     *
     */
    public String einsatzInfo(){
        Einsatz einsatz = selectEinsatz();
        return einsatz.getSonderattribute();
    }

    /**
     * Beendet einen zuvor ausgewählten Einsatz.
     * dabei werden die am Einsatz anwesenden Fahrzeuge an "wache.rueckkehr" übergeben und folgend der Einsatz gelöscht
     */
    public void beendeEinsatz() {
        Einsatz e = selectEinsatz();
        wache.rueckkehr(e.einsatzEnde());
        einsaetze.remove(e);
    }

    /**
     * Verlegt ein zuvor ausgewähltes Fahrzeug in die Wartungshalle.
     * Ein Fahrzeug mit der Fahrzeugnummer 0 oder -1 stellt hierbei einen Abbruch dar.
     */
    public void warteFahrzeug() {
        FahrzeugPopup fahrzeugPopup = new FahrzeugPopup();
        Fahrzeug fahrzeug = fahrzeugPopup.getData(wache.getFahrzeugeInFahrzeughalle());
        if (fahrzeug.fahrzeugnummer == -1 || fahrzeug.fahrzeugnummer == 0) {
            System.out.println("Fahrzeug auf Wartung setzen abgebrochen");
        } else {
            wache.fahreInWartungshalle(fahrzeug);
        }
    }

    /**
     * Reaktiviert ein Fahrzeug aus der Wartungshalle.
     * Ein Fahrzeug mit der Fahrzeugnummer 0 oder -1 stellt hierbei einen Abbruch dar.
     */
    public void reaktiviereFahrzeug() {
        FahrzeugPopup fahrzeugPopup = new FahrzeugPopup();
        Fahrzeug fahrzeug = fahrzeugPopup.getData(wache.getFahrzeugeInWartungshalle());
        if (fahrzeug.fahrzeugnummer == -1 || fahrzeug.fahrzeugnummer == 0) {
            System.out.println("Fahrzeug auf Wartung setzen abgebrochen");
        } else {
            wache.fahreInFahrzeughalle(fahrzeug);
        }
    }

    /**
     * Meldet einen zuvor ausgewählten Feuerwehrmann als erkrankt.
     * @param personalnummer die Personalnummer des Erkrankten.
     * hierbei stellt die Personalnummer 0 einen Aufruf auf das Popup zur auswahl eines Feuerwehrmanns
     * und die Personalnummer -1 einen Abbruch der Aktion dar
     */
    public void erkrankung(int personalnummer) {
        if (personalnummer == 0) {
            NummerAuswahlPopup t = new NummerAuswahlPopup();
            erkrankung(t.getData(wache.getActivePersonalnummern(), "Krankmeldung","Wer ist erkrankt?  "));

        } else if (personalnummer == -1) {
            System.out.println("Kranksetzen abgebrochen");
        } else {
            wache.makeKrank(personalnummer);
        }
    }
    /**
     * Meldet einen zuvor ausgewählten Feuerwehrmann als gesund.
     * @param personalnummer die Personalnummer des Erkrankten
     *  hierbei stellt die Personalnummer 0 einen Aufruf auf das Popup zur auswahl eines Feuerwehrmanns
     * und die Personalnummer -1 einen Abbruch der Aktion dar
     */
    public void gesund(int personalnummer) {
        if (personalnummer == 0) {
            NummerAuswahlPopup t = new NummerAuswahlPopup();
            gesund(t.getData(wache.getKrankPersonalnummern(), "Gesundmeldung", "Wer ist gesund?  "));

        } else if (personalnummer == -1) {
            System.out.println("Aktivsetzen abgebrochen");
        } else {
            wache.makeGesund(personalnummer);
        }
    }

    /**
     * Meldet einen Feuerwehrmann als im Urlaub.
     * @param personalnummer die Personalnummer des Beurlaubten
     * hierbei stellt die Personalnummer 0 einen Aufruf auf das Popup zur auswahl eines Feuerwehrmanns dar
     * und die Personalnummer -1 einen Abbruch der Aktion dar
     */
    public void urlaub(int personalnummer) {
        if (personalnummer == 0) {
            NummerAuswahlPopup t = new NummerAuswahlPopup();
            urlaub(t.getData(wache.getActivePersonalnummern(), "Urlaubsmeldung", "Wer geht in den Urlaub?  "));

        } else if (personalnummer == -1) {
            System.out.println("Beurlaubung abgebrochen");
        } else {
            wache.toUrlaub(personalnummer);
        }
    }

    /**
     * Meldet die Rückkehr eines Feuerwehrmanns aus dem Urlaub.
     * @param personalnummer die Personalnummer des aus dem Urlaub Zurückkehrenden
     * hierbei stellt die Personalnummer 0 einen Aufruf auf das Popup zur auswahl eines Feuerwehrmanns dar
     * und die Personalnummer -1 einen Abbruch der Aktion dar
     */
    public void zureuckVomUrlaub(int personalnummer) {
        if (personalnummer == 0) {
            NummerAuswahlPopup t = new NummerAuswahlPopup();
            zureuckVomUrlaub(t.getData(wache.getUrlaubPersonalnummern(),"Uhrlaubsrückkehr", "Wer ist aus dem Urlaub zurück?  "));

        } else if (personalnummer == -1) {
            System.out.println("Aktivsetzen abgebrochen");
        } else {
            wache.fromUrlaub(personalnummer);
        }
        /*Funktion in anderer klasse aufrufen, im gui alle beurlaubten Personen in Dropdown auflisten*/
    }

    /**
     * Teambildung für einen Einsatz.
     *
     * @return True, wenn das Team für den Einsatz gebildet wurde, sonst False.
     */
    public boolean teamZuEinsatz() {
        Einsatz e = selectEinsatz();
        if (wache.moeglicheEinsatzArten().contains(e.getEinsatzArt())) {


            if (e.getEinsatznummer() != -1) {
                ArrayList<LkwFahrer> reservierteMtfFahrer = new ArrayList<LkwFahrer>();
                if (e.getBenoetigteMTF() > 0) {
                    for (int i = 0; i < e.getBenoetigteMTF(); i++) {
                        reservierteMtfFahrer.add((LkwFahrer) wache.generateBesatzung(1, true).getFirst());
                        e.bedieneFeuerwehrmann(1);
                    }
                }
                for (int i = 0; i < e.getBenoetigteELW(); ) {
                    ELW fahrzeug = (ELW) wache.fahrzeugZuEinsatz(Fahrzeugkategorie.ELW);
                    int benoetigteFeuerwehrleute = fahrzeug.getSitze();
                    if (fahrzeug.getSitze() > e.getBenoetigteFeuerwehrleute()) {
                        benoetigteFeuerwehrleute = e.getBenoetigteFeuerwehrleute();
                    }
                    ArrayList<Feuerwehrmann> besatzung = wache.generateBesatzung(benoetigteFeuerwehrleute, fahrzeug.getArt() == Fahrzeug.FahrzeugArt.LKW);
                    fahrzeug.aufsitzen(besatzung);
                    Dienstgrad dienstgrad = Dienstgrad.D_DIENST;
                    for (Feuerwehrmann f : besatzung) {
                        if (f.getDienstgrad().istHoeherAls(dienstgrad)) {
                            dienstgrad = f.getDienstgrad();
                        }
                    }
                    fahrzeug.setDienstgrad(dienstgrad);
                    e.bedieneElw(1);
                    e.bedieneFeuerwehrmann(besatzung.size());
                    e.fahrzeugeEinbeziehen(fahrzeug);
                }
                for (int i = 0; i < e.getBenoetigteTLF(); ) {
                    Fahrzeug fahrzeug = wache.fahrzeugZuEinsatz(Fahrzeugkategorie.TLF);
                    int benoetigteFeuerwehrleute = fahrzeug.getSitze();
                    if (fahrzeug.getSitze() > e.getBenoetigteFeuerwehrleute()) {
                        benoetigteFeuerwehrleute = e.getBenoetigteFeuerwehrleute();
                    }
                    ArrayList<Feuerwehrmann> besatzung = wache.generateBesatzung(benoetigteFeuerwehrleute, fahrzeug.getArt() == Fahrzeug.FahrzeugArt.LKW);
                    fahrzeug.aufsitzen(besatzung);
                    e.bedieneTlf(1);
                    e.bedieneFeuerwehrmann(besatzung.size());
                    e.fahrzeugeEinbeziehen(fahrzeug);
                }
                for (int i = 0; i < e.getBenoetigteDLK(); ) {
                    Fahrzeug fahrzeug = wache.fahrzeugZuEinsatz(Fahrzeugkategorie.DLK);
                    int benoetigteFeuerwehrleute = fahrzeug.getSitze();
                    if (fahrzeug.getSitze() > e.getBenoetigteFeuerwehrleute()) {
                        benoetigteFeuerwehrleute = e.getBenoetigteFeuerwehrleute();
                    }
                    ArrayList<Feuerwehrmann> besatzung = wache.generateBesatzung(benoetigteFeuerwehrleute, fahrzeug.getArt() == Fahrzeug.FahrzeugArt.LKW);
                    fahrzeug.aufsitzen(besatzung);
                    e.bedieneDlk(1);
                    e.bedieneFeuerwehrmann(besatzung.size());
                    e.fahrzeugeEinbeziehen(fahrzeug);
                }
                for (int i = 0; i < e.getBenoetigteMTF(); ) {
                    Fahrzeug fahrzeug = wache.fahrzeugZuEinsatz(Fahrzeugkategorie.MTF);
                    int benoetigteFeuerwehrleute = fahrzeug.getSitze() - 1;
                    if (fahrzeug.getSitze() - 1 > e.getBenoetigteFeuerwehrleute()) {
                        benoetigteFeuerwehrleute = e.getBenoetigteFeuerwehrleute() - 1;
                    }
                    ArrayList<Feuerwehrmann> besatzung = new ArrayList<Feuerwehrmann>();
                    besatzung.add(reservierteMtfFahrer.getFirst());
                    reservierteMtfFahrer.removeFirst();
                    if (benoetigteFeuerwehrleute != 0) {
                        besatzung.addAll(wache.generateBesatzung(benoetigteFeuerwehrleute, false));
                    }
                    fahrzeug.aufsitzen(besatzung);
                    e.bedieneMTF(1);
                    e.bedieneFeuerwehrmann(besatzung.size());
                    e.fahrzeugeEinbeziehen(fahrzeug);


                }
                return true;

            }
        }

        return false;


    }

    /**
     * Überprüft, ob das Beenden des Programms möglich ist, indem geprüft wird, ob keine Einsätze mehr offen sind.
     *
     * @return true, wenn das Programm beendet werden kann (sprich keine offenen Einsätze), andernfalls false.
     */
    public boolean programBeendenMoeglich(){
        return einsaetze.isEmpty();
    }
}

