import java.util.ArrayList;

public class Leitstelle {
    private Wache wache = new Wache();
    private ArrayList<Einsatz> einsatzs = new ArrayList<Einsatz>();


    public Leitstelle(){
        genrateFeuerwache();
    }


    private void genrateFeuerwache() { // Hier werden die gegebenen Daten in die Struktur des Programms eingesetzt
        for (int i = 1; i<=80; i++) {
            Feuerwehrmann feuerwehrmann;
            if (i<=10) {
                feuerwehrmann = new lkwFahrer(i);
            }
            else {
                feuerwehrmann = new pkwFahrer(i);
            }
            wache.addPersonal(feuerwehrmann);
        }
        for (int i = 1; i<=4; i++ ){
            Fahrzeug elw = new ELW(i,Status.ZWEI);
            wache.addFahrzeug(elw);
        }
        for (int i = 1; i <= 5; i++){
            Fahrzeug tlf = new TLF(i,Status.ZWEI);
            wache.addFahrzeug(tlf);
        }
        for (int i = 1; i<=4; i++){
            Fahrzeug mtf = new MTF(i,Status.ZWEI,2004);
            wache.addFahrzeug(mtf);
        }
        for (int i = 1; i <= 5; i++){
            Fahrzeug dlk = new DLK(i,Status.ZWEI,20);
            wache.addFahrzeug(dlk);
        }
    }

    public void createEinsatz(int einsatzNummer, EinsatzArt einsatzArt){
        //vlt per Switch Case verfahren, da einsatzarten eigene klassen und konstruktoren haben
        //einsatznummer evtl automatisch generieren
    }
    public void beendeEinsatz(int einsatzNummer){
        //Mithilfe der Einsatznummer den Einsatz beenden
        //bedenken alle Fahrzeuge und Feuerwehrmänner zurück zu buchen
    }
    public void warteFahrzeug(int fahrzeugnummer /*fahrzeugart übergeben, ggf generierung der Fahrzeugnummer anpassen*/){
        //Simples umbuchen vom Fahrzeug
    }
    public void reactivateFahrzeug(int fahrzeugnummer /*wie bei warteFahrzeug()*/){
        /*Simples umbuchen vom Fahrzeug*/
    }
    public void erkrankung(int personalnummer){
        if (personalnummer == 0) {
            PersonalPopup t = new PersonalPopup();
            erkrankung(t.getData(wache.getActivePersonalnummern(),"Wer ist erkrankt?  "));

        } else if (personalnummer == -1) {
            System.out.println("Kranksetzen abgebrochen");
        } else {
            wache.makeKrank(personalnummer);
        }
    }
    public void gesund(int personalnummer){
        if (personalnummer == 0) {
            PersonalPopup t = new PersonalPopup();
            gesund(t.getData(wache.getKrankPersonalnummern(),"Wer ist erkrankt?  "));

        } else if (personalnummer == -1) {
            System.out.println("Aktivsetzen abgebrochen");
        } else {
            wache.makeGesund(personalnummer);
        }
        /*Funktion in anderer klasse aufrufen, im gui alle kranken Personen in Dropdown auflisten*/
    }
    public void urlaub(int personalnummer){
        if (personalnummer == 0) {
            PersonalPopup t = new PersonalPopup();
            urlaub(t.getData(wache.getActivePersonalnummern(),"Wer ist erkrankt?  "));

        } else if (personalnummer == -1) {
            System.out.println("Beurlaubung abgebrochen");
        } else {
            wache.toUrlaub(personalnummer);
        }
        /*Funktion in anderer klasse aufrufen, im gui alle gesunden Personen in Dropdown auflisten*/
    }
    public void backToWork(int personalnummer){
        if (personalnummer == 0) {
            PersonalPopup t = new PersonalPopup();
            backToWork(t.getData(wache.getUrlaubPersonalnummern(),"Wer ist aus dem Urlaub zurück?  "));

        } else if (personalnummer == -1) {
            System.out.println("Aktivsetzen abgebrochen");
        } else {
            wache.fromUrlaub(personalnummer);
        }
        /*Funktion in anderer klasse aufrufen, im gui alle beurlaubten Personen in Dropdown auflisten*/
    }
    public void teamToEinsatz(){
        /*Personen können durch Funktion besorgt werden,Fahrzeuge nach erfordern verbuchen und Personen hinzufügen, im GUI auswahl des einsatzes*/
        /*Weitergedacht: Mehr PPersonal hinzufügen, aber erst wenn das Programm läuft*/
    }
    public void stopProgram(){
        /*Nicht Erlauben wenn einsätze offen, Weitergedacht: Speichern und beim Start vom Programm nach letztem Save suchen, falls vorhanden*/
        /*Bei Erster version: Sollen wir einen Force Close Knopf einbauen?  Ja:felix,  Nein: */
        /*Just For Fun: Ne kurze Animation*/
    }
}

