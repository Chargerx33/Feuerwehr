import javax.swing.*;
import java.awt.event.*;

public class Test1 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton button1;
    private JCheckBox checkBox1;
    private JTabbedPane tabbedPane1;
    private JSlider slider1;
    private JSlider slider2;

    public Test1() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(
                e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );
    }

    private void onOK() {
        // add your code here
        System.out.println("OK");
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        System.out.println("The Earth is Flat");
        dispose();
    }

    /*public static void main(String[] args) {
        Test1 dialog = new Test1();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
    */
}