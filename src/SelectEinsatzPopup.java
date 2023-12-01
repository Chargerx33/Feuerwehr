import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SelectEinsatzPopup {
        public int getData(ArrayList<Integer> einsatznummern) {
            JComboBox<Integer> dropdown = new JComboBox<Integer>();
            for (int i : einsatznummern) {
                dropdown.addItem(i);
            }

            JPanel panel = new JPanel(new GridLayout(0, 2));
            panel.add(new JLabel("Einsatz:  "));
            panel.add(dropdown);


            int result = JOptionPane.showConfirmDialog(null, panel, "Wähle den Einsatz!", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                return (int) dropdown.getSelectedItem();
            } else if (result == JOptionPane.OK_CANCEL_OPTION) {
                return -1;
            }
            return 0;
        }
}
