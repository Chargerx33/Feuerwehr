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
        /*Funktion in anderer klasse aufrufen, im gui alle gesunden Personen in Dropdown auflisten*/
    }
    public void gesund(int Personalnummer){
        /*Funktion in anderer klasse aufrufen, im gui alle kranken Personen in Dropdown auflisten*/
    }
    public void Urlaub(int personalnummer){
        /*Funktion in anderer klasse aufrufen, im gui alle gesunden Personen in Dropdown auflisten*/
    }
    public void backToWork(int personalnummer){
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

