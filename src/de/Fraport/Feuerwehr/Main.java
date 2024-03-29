package de.Fraport.Feuerwehr;

import java.util.ArrayList;

/**
 * Die Hauptklasse des Programms, die die Leitstelle initialisiert und das Hauptfenster öffnet.
 */
public class Main {

    /**
     * Die Hauptmethode des Programms, die die Leitstelle initialisiert und das Hauptfenster öffnet.
     *
     * @param args Die Kommandozeilenargumente (werden in diesem Fall nicht verwendet).
     */
    public static void main(String[] args) {
        // Initialisiere die Leitstelle
        ArrayList<Leitstelle> leitstelles = new ArrayList<Leitstelle>();
        Leitstelle leitstelle = new Leitstelle();
        leitstelles.add(leitstelle);
        if (!(leitstelles.contains(leitstelle))) {
            leitstelles.add(leitstelle);
        }

        // Öffnet das Hauptfenster und übergibt die Leitstelle und den Titel
        Hauptfenster dialog = new Hauptfenster(leitstelle,"Inferno-Control v1.0.0");
        dialog.pack();
        dialog.setVisible(true);

        //für Debugging zum anhalten nach einem Programmdurchlauf
        System.out.println("Program beendet");
        System.exit(0);
    }

}