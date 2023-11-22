import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/*
public class Test1 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;

    private Leitstelle leitstelle;
    private Wache wache;
    public int Output;
    public Test1(Wache wache, Leitstelle leitstelle) {
        this.leitstelle = leitstelle;
        this.wache = wache;
        for (int i: wache.getActivePersonalnummern()) {
            comboBox1.addItem(i);
        }


        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        System.out.println(comboBox1.getSelectedItem());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        Output = Integer.parseInt(comboBox1.getSelectedItem().toString());
        dispose();
    }

    /*public static void main(String[] args) {
        Test1 dialog = new Test1();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);

    }


}
*/

public class PersonalPopup {
    public int getData(ArrayList<Integer> personal, String text) {
        JComboBox<Integer> dropdown = new JComboBox<Integer>();
        for (int i : personal) {
            dropdown.addItem(i);
        }

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Personalnummer:  "));
        panel.add(dropdown);


        int result = JOptionPane.showConfirmDialog(null, panel, text, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return (int) dropdown.getSelectedItem();
        } else if (result == JOptionPane.OK_CANCEL_OPTION) {
            return -1;
        }
        return 0;
    }
}