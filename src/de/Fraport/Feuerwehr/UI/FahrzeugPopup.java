package de.Fraport.Feuerwehr.UI;

import de.Fraport.Feuerwehr.Fahrzeug;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Klasse, die ein Popup zur Auswahl eines Fahrzeugs erstellt.
 */
public class FahrzeugPopup {

    /**
     * Zeigt ein Popup zur Auswahl eines Fahrzeugs an.
     *
     * @param fahrzeuge Eine Liste von Fahrzeugen, aus denen ausgewählt werden kann.
     * @return Das ausgewählte Fahrzeug oder ein neues Fahrzeug mit Standardwerten als Repräsentation eines Abbruchs.
     */
    public Fahrzeug getData(ArrayList<Fahrzeug> fahrzeuge) {
        JComboBox<String> dropdown = new JComboBox<String>();
        for (Fahrzeug f : fahrzeuge) {

            dropdown.addItem(f.getFahrzeugKategorie().toString() + "_" + f.getFahrzeugnummer().toString());
        }

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Fahrzeug:  "));
        panel.add(dropdown);


        int result = JOptionPane.showConfirmDialog(null, panel, "Fahrzeug Wählen", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String ausgewaelt = dropdown.getSelectedItem().toString();
            for (Fahrzeug f : fahrzeuge) {

                String fahrzeug = f.getFahrzeugKategorie().toString() + "_" + f.getFahrzeugnummer().toString();
                if (fahrzeug.equals(ausgewaelt)) return f;
            }
        } else if (result == JOptionPane.OK_CANCEL_OPTION) {
            return new Fahrzeug();
        }
        return new Fahrzeug();
    }
}