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

    private void createEinsatz(int einsatzNummer /*übergabe von der EinsatzArt*/){
        //vlt per Switch Case verfahren, da einsatzarten eigene klassen und konstruktoren haben

    }
}

