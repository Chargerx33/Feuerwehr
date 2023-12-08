package de.Fraport.Feuerwehr.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Generische Klasse für die Auswahl einer Nummer aus einer Dropdown-Liste.
 */
public class NummerAuswahlPopup {
    /**
     * Zeigt einen Dialog an, um eine Nummer auszuwählen.
     *
     * @param integers Eine Liste der auswählbaren Nummern.
     * @param titel   Der Titel des Dialogs.
     * @param text    Der Text, der die Auswahl beschreibt.
     * @return Die ausgewählte Nummer oder {@code -1} bzw. {@code 0}, was einen Abbruch darstellt.
     */
    public int getData(ArrayList<Integer> integers, String titel, String text) {
        JComboBox<Integer> dropdown = new JComboBox<Integer>();
        for (int i : integers) {
            dropdown.addItem(i);
        }

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel(text));
        panel.add(dropdown);


        int result = JOptionPane.showConfirmDialog(null, panel, titel, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return (int) dropdown.getSelectedItem();
        } else if (result == JOptionPane.OK_CANCEL_OPTION) {
            return -1;
        }
        return -1;
    }
}
