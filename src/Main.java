
public class Main {
    static Wache wache = new Wache();
    public static void main(String[] args) {
        genrateFeuerwache();
        System.out.println(0);
    }

    private static void genrateFeuerwache() { // Hier werden die gegebenen Daten in die Struktur des Programms eingesetzt
        for (int i = 1; i<=80; i++) {
            boolean lkwFahrer = i <= 10;
            Feuerwehrmann feuerwehrmann = new Feuerwehrmann(lkwFahrer,i);
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
        System.out.println(wache.fahrzeughalle.get(0) instanceof DLK);// Besipiel zum Prüfen, welches Fahrzeug
        System.out.println(wache.fahrzeughalle.get(0) instanceof ELW);
    }
}