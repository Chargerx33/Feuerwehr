import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EinsatzArtPopup {
    public EinsatzArt getData() {
        JComboBox<EinsatzArt> dropdown = new JComboBox<EinsatzArt>();
        for (EinsatzArt e : EinsatzArt.values()) {
            dropdown.addItem(e);
        }

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Einsatzart:  "));
        panel.add(dropdown);


        int result = JOptionPane.showConfirmDialog(null, panel, "Einsatzart Wählen", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return (EinsatzArt) dropdown.getSelectedItem();
        } else {
            return EinsatzArt.UNDEFINED;
        }
    }
}

