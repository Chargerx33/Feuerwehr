import javax.swing.*;
import java.awt.event.*;

public class MainWindow extends JDialog {
    Leitstelle leitstelle;
    private JPanel contentPane;
    private JButton urlaubsmeldungButton;
    private JButton urlaubsrueckkehrButton;
    private JButton erkrankungButton;
    private JButton gesundmeldungButton;
    private JButton fahrzeugVerfuegbarButton;
    private JButton fahrzeugWartungButton;
    private JButton neuerEinsatzButton;
    private JButton einsatzBeendenButton;
    private JButton einsatzAnfahrenButton;
    private JButton programmBeendenButton;
    private JButton einsatzInfoButton;

    public MainWindow(Leitstelle leitstelle) {
        this.leitstelle = leitstelle;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(urlaubsmeldungButton);

        urlaubsmeldungButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onUrlaubsmeldung();
            }
        });
        urlaubsrueckkehrButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onUrlaubsrueckkehr();
            }
        });
        fahrzeugWartungButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onFahrzeugWartung();
            }
        });
        erkrankungButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onErkrankung();
            }
        });
        gesundmeldungButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onGesund();
            }
        });
        fahrzeugVerfuegbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onFahrzeugVerfuegbar();
            }
        });
        neuerEinsatzButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onNeuerEinsatz();
            }
        });
        einsatzAnfahrenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onEinsatzAnfahren();
            }
        });
        einsatzInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onEinsatzInfo();
            }
        });
        einsatzBeendenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onEinsatzEnde();
            }
        });
        programmBeendenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        });
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onExit();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        einsatzInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void onUrlaubsmeldung() {
        leitstelle.urlaub(0);

    }

    private void onUrlaubsrueckkehr() {
        leitstelle.backToWork(0);

    }
    private void onFahrzeugWartung() {
        //Noch machen!!!
    }
    private void onFahrzeugVerfuegbar() {
        //Noch machen!!!
    }
    private void onErkrankung(){
        leitstelle.erkrankung(0);
    }
    private void onGesund(){
        leitstelle.gesund(0);
    }
    private void onNeuerEinsatz(){
        leitstelle.createEinsatz();
    }
    private void onEinsatzAnfahren(){
        leitstelle.teamZuEinsatz();
    }
    private void onEinsatzInfo(){
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame,
                "Eggs are not supposed to be green.",
                "Info zum ausgewählten Einsatz",
                JOptionPane.PLAIN_MESSAGE);
    }
    private void onEinsatzEnde(){
        leitstelle.beendeEinsatz();
    }
    private void onExit(){
        dispose();
    }

}