import java.util.ArrayList;
import java.util.Collections;

public class Leitstelle {
    private Wache wache = new Wache();
    private ArrayList<Einsatz> einsaetze = new ArrayList<Einsatz>();


    public Leitstelle() {
        genrateFeuerwache();
    }


    private void genrateFeuerwache() { // Hier werden die gegebenen Daten in die Struktur des Programms eingesetzt
        for (int i = 1; i <= 80; i++) {
            Feuerwehrmann feuerwehrmann;
            if (i <= 10) {
                feuerwehrmann = new lkwFahrer(i);
            } else {
                feuerwehrmann = new pkwFahrer(i);
            }
            wache.addPersonal(feuerwehrmann);
        }
        for (int i = 1; i <= 4; i++) {
            Fahrzeug elw = new ELW(i);
            wache.addFahrzeug(elw);
        }
        for (int i = 1; i <= 5; i++) {
            Fahrzeug tlf = new TLF(i);
            wache.addFahrzeug(tlf);
        }
        for (int i = 1; i <= 4; i++) {
            Fahrzeug mtf = new MTF(i, 2004);
            wache.addFahrzeug(mtf);
        }
        for (int i = 1; i <= 5; i++) {
            Fahrzeug dlk = new DLK(i, 23);
            wache.addFahrzeug(dlk);
        }
    }

    public void neuerEinsatz() {
        EinsatzArtPopup eap = new EinsatzArtPopup();
        EinsatzArt e = eap.getData();
        Einsatz einsatz = new Einsatz(errechneEinsatzNummer(), e);
        if (e != EinsatzArt.UNDEFINED) {

            einsaetze.add(einsatz);
        } else {
            System.out.println("Einsatzaufnahme abgebrochen");
        }
        System.out.println(einsatz);
        //vlt per Switch Case verfahren, da einsatzarten eigene klassen und konstruktoren haben
        //einsatznummer evtl automatisch generieren
    }

    private int errechneEinsatzNummer() {
        int highest = 0;
        for (Einsatz e : einsaetze) {
            if (e.getEinsatzNummer() > highest) highest = e.getEinsatzNummer();
        }
        return (highest + 1);
    }
    public String statusDerWache() {
        return wache.statusDerWache();
    }

    private Einsatz selectEinsatz() {
        ArrayList<Integer> einsatznummern = new ArrayList<Integer>();
        for (Einsatz e : einsaetze) {
            einsatznummern.add(e.getEinsatzNummer());
        }
        Collections.sort(einsatznummern);
        SelectEinsatzPopup selectEinsatzPopup = new SelectEinsatzPopup();
        int selected = selectEinsatzPopup.getData(einsatznummern);
        for (Einsatz e : einsaetze) {
            if (e.getEinsatzNummer() == selected) {
                return e;
            }
        }
        return new Einsatz(-1, EinsatzArt.UNDEFINED);
    }
    public String einsatzInfo(){
        Einsatz einsatz = selectEinsatz();
        return einsatz.getSonderatribute();
    }

    public void beendeEinsatz() {
        Einsatz e = selectEinsatz();
        wache.rueckkehr(e.einsatzEnde());
        einsaetze.remove(e);
    }

    public void warteFahrzeug() {
        FahrzeugPopup fahrzeugPopup = new FahrzeugPopup();
        Fahrzeug fahrzeug = fahrzeugPopup.getData(wache.getFahrzeugeInFahrzeughalle());
        wache.fahreInWartungshalle(fahrzeug);
    }

    public void reaktiviereFahrzeug() {
        FahrzeugPopup fahrzeugPopup = new FahrzeugPopup();
        Fahrzeug fahrzeug = fahrzeugPopup.getData(wache.getFahrzeugeInWartungshalle());
        wache.fahreInFahrzeughalle(fahrzeug);
    }

    public void erkrankung(int personalnummer) {
        if (personalnummer == 0) {
            PersonalPopup t = new PersonalPopup();
            erkrankung(t.getData(wache.getActivePersonalnummern(), "Wer ist erkrankt?  "));

        } else if (personalnummer == -1) {
            System.out.println("Kranksetzen abgebrochen");
        } else {
            wache.makeKrank(personalnummer);
        }
    }

    public void gesund(int personalnummer) {
        if (personalnummer == 0) {
            PersonalPopup t = new PersonalPopup();
            gesund(t.getData(wache.getKrankPersonalnummern(), "Wer ist gesund?  "));

        } else if (personalnummer == -1) {
            System.out.println("Aktivsetzen abgebrochen");
        } else {
            wache.makeGesund(personalnummer);
        }
        /*Funktion in anderer klasse aufrufen, im gui alle kranken Personen in Dropdown auflisten*/
    }

    public void urlaub(int personalnummer) {
        if (personalnummer == 0) {
            PersonalPopup t = new PersonalPopup();
            urlaub(t.getData(wache.getActivePersonalnummern(), "Wer geht in den Urlaub?  "));

        } else if (personalnummer == -1) {
            System.out.println("Beurlaubung abgebrochen");
        } else {
            wache.toUrlaub(personalnummer);
        }
        /*Funktion in anderer klasse aufrufen, im gui alle gesunden Personen in Dropdown auflisten*/
    }

    public void backToWork(int personalnummer) {
        if (personalnummer == 0) {
            PersonalPopup t = new PersonalPopup();
            backToWork(t.getData(wache.getUrlaubPersonalnummern(), "Wer ist aus dem Urlaub zurück?  "));

        } else if (personalnummer == -1) {
            System.out.println("Aktivsetzen abgebrochen");
        } else {
            wache.fromUrlaub(personalnummer);
        }
        /*Funktion in anderer klasse aufrufen, im gui alle beurlaubten Personen in Dropdown auflisten*/
    }

    public boolean teamZuEinsatz() {
        /*Personen können durch Funktion besorgt werden,Fahrzeuge nach erfordern verbuchen und Personen hinzufügen, im GUI auswahl des einsatzes*/
        /*Weitergedacht: Mehr PPersonal hinzufügen, aber erst wenn das Programm läuft*/
        Einsatz e = selectEinsatz();
        if (wache.moeglicheEinsatzArten().contains(e.getEinsatzArt())) {


            if (e.getEinsatzNummer() != -1) {
                ArrayList<lkwFahrer> reservierteMtfFahrer = new ArrayList<lkwFahrer>();
                if (e.getBenoetigteMtf() > 0) {
                    for (int i = 0; i < e.getBenoetigteMtf(); i++) {
                        reservierteMtfFahrer.add((lkwFahrer) wache.generateBesatzung(1, true).getFirst());
                        e.bedieneFeuerwehrmann(1);
                    }
                }
                for (int i = 0; i < e.getBenoetigteElw(); ) {
                    ELW fahrzeug = (ELW) wache.fahrzeugZuEinsatz(FahrzeugKategorie.ELW);
                    int benoetigteFeuerwehrleute = fahrzeug.getSitze();
                    if (fahrzeug.getSitze() > e.getBenoetigteFeuerwehrleute()) {
                        benoetigteFeuerwehrleute = e.getBenoetigteFeuerwehrleute();
                    }
                    ArrayList<Feuerwehrmann> besatzung = wache.generateBesatzung(benoetigteFeuerwehrleute, fahrzeug.getTyp() == FahrzeugArt.LKW);
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
                for (int i = 0; i < e.getBenoetigteTlf(); ) {
                    Fahrzeug fahrzeug = wache.fahrzeugZuEinsatz(FahrzeugKategorie.TLF);
                    int benoetigteFeuerwehrleute = fahrzeug.getSitze();
                    if (fahrzeug.getSitze() > e.getBenoetigteFeuerwehrleute()) {
                        benoetigteFeuerwehrleute = e.getBenoetigteFeuerwehrleute();
                    }
                    ArrayList<Feuerwehrmann> besatzung = wache.generateBesatzung(benoetigteFeuerwehrleute, fahrzeug.getTyp() == FahrzeugArt.LKW);
                    fahrzeug.aufsitzen(besatzung);
                    e.bedieneTlf(1);
                    e.bedieneFeuerwehrmann(besatzung.size());
                    e.fahrzeugeEinbeziehen(fahrzeug);
                }
                for (int i = 0; i < e.getBenoetigteDlk(); ) {
                    Fahrzeug fahrzeug = wache.fahrzeugZuEinsatz(FahrzeugKategorie.DLK);
                    int benoetigteFeuerwehrleute = fahrzeug.getSitze();
                    if (fahrzeug.getSitze() > e.getBenoetigteFeuerwehrleute()) {
                        benoetigteFeuerwehrleute = e.getBenoetigteFeuerwehrleute();
                    }
                    ArrayList<Feuerwehrmann> besatzung = wache.generateBesatzung(benoetigteFeuerwehrleute, fahrzeug.getTyp() == FahrzeugArt.LKW);
                    fahrzeug.aufsitzen(besatzung);
                    e.bedieneDlk(1);
                    e.bedieneFeuerwehrmann(besatzung.size());
                    e.fahrzeugeEinbeziehen(fahrzeug);
                }
                for (int i = 0; i < e.getBenoetigteMtf(); ) {
                    Fahrzeug fahrzeug = wache.fahrzeugZuEinsatz(FahrzeugKategorie.MTF);
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

    public void stopProgram() {
        /*Nicht Erlauben wenn einsätze offen, Weitergedacht: Speichern und beim Start vom Programm nach letztem Save suchen, falls vorhanden*/
        /*Bei Erster version: Sollen wir einen Force Close Knopf einbauen?  Ja:felix,  Nein: */
        /*Just For Fun: Ne kurze Animation*/
    }
}

