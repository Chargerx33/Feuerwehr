import javax.swing.*;
import java.awt.event.*;

public class Main {

    public static void main(String[] args) {
        Leitstelle leitstelle = new Leitstelle();
        leitstelle.erkrankung(0);
        MainWindow mainWindow = new MainWindow();
        mainWindow.pack();
        mainWindow.setVisible(true);
        /*Test1 dialog = new Test1();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);*/
        System.out.println(1);
    }

}