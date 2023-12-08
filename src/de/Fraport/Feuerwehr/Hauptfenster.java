package de.Fraport.Feuerwehr;

import javax.swing.*;
import java.awt.event.*;

/**
 * Die Klasse `Hauptfenster` repräsentiert das Hauptfenster der Leitstelle.<p>
 * Sie enthält Buttons für verschiedene Aktionen, um die Leitstellenfunktionen zu steuern.
 * <p>
 * Die Klasse erbt von `JDialog` und wird mit einer Instanz der Leitstelle initialisiert.<p>
 * Das Aussehen des Hauptfensters wird durch Hauptfenster.form weiter beschrieben.<p>
 * Hauptfenster.form, wurde durch mithilfe des Swing UI Designer von IntelliJ Ultimate erzeugt.
 */
public class Hauptfenster extends JDialog {
    /**
     * Die Referenz auf die Leitstelle, mit der dieses Hauptfenster verbunden ist.
     */
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
    private JTabbedPane tabbedPane1;
    private JLabel label;

    /**
     * Konstruktor für das Hauptfenster der Leitstelle.
     *
     * @param leitstelle Die Instanz der Leitstelle, die dieses Hauptfenster steuert.
     * @param titel Der Titel des Fensters
     */
    public Hauptfenster(Leitstelle leitstelle, String titel) {
        this.leitstelle = leitstelle;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(urlaubsmeldungButton);
        this.setTitle(titel);



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
        updateStatusDerWache();
    }

    /**
     * Aktualisiert die Anzeige des Status der Wache im GUI-Label.<p>
     * Hierbei wird zur formatierung des Texts HTML verwendet
     */
    private void updateStatusDerWache() {
        label.setText("<html>" + leitstelle.statusDerWache().replaceAll("\n", "<br/>") + "</html");
    }

    /**
     * Behandelt den Klick auf den "Urlaubsmeldung" Button.<p>
     * Ruft die entsprechende Methode in der Leitstelle auf und aktualisiert den Status der Wache.
     */
    private void onUrlaubsmeldung() {
        leitstelle.urlaub(0);
        updateStatusDerWache();
    }

    /**
     * Behandelt den Klick auf den "Urlaubsrückkehr" Button.<p>
     * Ruft die entsprechende Methode in der Leitstelle auf und aktualisiert den Status der Wache.
     */
    private void onUrlaubsrueckkehr() {
        leitstelle.zureuckVomUrlaub(0);
        updateStatusDerWache();
    }

    /**
     * Behandelt den Klick auf den "Fahrzeug Wartung" Button.<p>
     * Ruft die entsprechende Methode in der Leitstelle auf und aktualisiert den Status der Wache.
     */
    private void onFahrzeugWartung() {
        leitstelle.warteFahrzeug();
        updateStatusDerWache();
    }
    /**
     * Behandelt den Klick auf den "Fahrzeug Verfügbar" Button.<p>
     * Ruft die entsprechende Methode in der Leitstelle auf und aktualisiert den Status der Wache.
     */
    private void onFahrzeugVerfuegbar() {
        leitstelle.reaktiviereFahrzeug();
        updateStatusDerWache();
    }

    /**
     * Behandelt den Klick auf den "Erkrankung" Button.<p>
     * Ruft die entsprechende Methode in der Leitstelle auf und aktualisiert den Status der Wache.
     */
    private void onErkrankung(){
        leitstelle.erkrankung(0);
        updateStatusDerWache();
    }
    /**
     * Behandelt den Klick auf den "Gesundmeldung" Button.<p>
     * Ruft die entsprechende Methode in der Leitstelle auf und aktualisiert den Status der Wache.
     */
    private void onGesund(){
        leitstelle.gesund(0);
        updateStatusDerWache();
    }

    /**
     * Behandelt den Klick auf den "Neuer Einsatz" Button.<p>
     * Ruft die entsprechende Methode in der Leitstelle auf.
     */
    private void onNeuerEinsatz(){
        leitstelle.neuerEinsatz();
    }

    /**
     * Behandelt den Klick auf den "Einsatz Anfahren" Button.<p>
     * Ruft die entsprechende Methode in der Leitstelle auf und zeigt eine Fehlermeldung, falls nicht genügend Einsatzmittel vorhanden sind.
     */
    private void onEinsatzAnfahren(){
        boolean angefahren =  leitstelle.teamZuEinsatz();
        if (!angefahren) {

            JOptionPane.showMessageDialog(null,"Nicht genügend Einsatzkräfte/Fahrzeuge","Anfahren nicht möglich!", JOptionPane.ERROR_MESSAGE);
        }
        updateStatusDerWache();
    }
    /**
     * Behandelt den Klick auf den "Einsatz Info" Button.<p>
     * Ruft die entsprechende Methode in der Leitstelle auf und zeigt die Informationen in einem Dialogfenster an.
     */
    private void onEinsatzInfo(){
        String text = leitstelle.einsatzInfo();
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame,
                text,
                "Info zum ausgewählten Einsatz",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Behandelt den Klick auf den "Einsatz Beenden" Button.<p>
     * Ruft die entsprechende Methode in der Leitstelle auf und aktualisiert den Status der Wache.
     */
    private void onEinsatzEnde(){
        leitstelle.beendeEinsatz();
        updateStatusDerWache();
    }
    /**
     * Behandelt den Klick auf den "Programm Beenden" Button.<p>
     * Überprüft, ob das Beenden des Programms möglich ist, und zeigt ggf. eine Fehlermeldung an.<p>
     * Schließt das Hauptfenster, wenn das Beenden möglich ist.
     */
    private void onExit(){
        if (leitstelle.programBeendenMoeglich()) {
            dispose();
        }
        else {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    "Das beenden des Programs ist nicht möglich, da noch mindestens ein Einsatz offen ist.",
                    "Beenden nicht möglich!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
