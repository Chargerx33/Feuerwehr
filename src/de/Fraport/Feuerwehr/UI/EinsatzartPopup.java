package de.Fraport.Feuerwehr.UI;

import de.Fraport.Feuerwehr.Enumerations.Einsatzart;

import javax.swing.*;
import java.awt.*;

public class EinsatzartPopup {
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

