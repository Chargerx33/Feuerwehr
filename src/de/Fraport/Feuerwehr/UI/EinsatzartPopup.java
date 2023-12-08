package de.Fraport.Feuerwehr.UI;

import de.Fraport.Feuerwehr.Enumerations.Einsatzart;

import javax.swing.*;
import java.awt.*;

/**
 * Generische Klasse für die Auswahl einer Einsatzart aus einer Dropdown-Liste.
 */
public class EinsatzartPopup {
    /**
     * Zeigt einen Dialog an, um eine Einsatzart auszuwählen.
     * @return Die ausgewählte Einsatzart oder {@code UNDEFINED}, was einen Abbruch darstellt.
     */
    public Einsatzart getData() {
        JComboBox<Einsatzart> dropdown = new JComboBox<Einsatzart>();
        for (Einsatzart e : Einsatzart.values()) {
            if (e != Einsatzart.UNDEFINED) {
                dropdown.addItem(e);
            }
        }

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Einsatzart:  "));
        panel.add(dropdown);


        int result = JOptionPane.showConfirmDialog(null, panel, "Einsatzart Wählen", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return (Einsatzart) dropdown.getSelectedItem();
        } else {
            return Einsatzart.UNDEFINED;
        }
    }
}

