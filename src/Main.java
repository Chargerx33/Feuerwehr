/**
 * ☃
 */
public class Main {

    public static void main(String[] args) {
        Leitstelle leitstelle = new Leitstelle();
        MainWindow dialog = new MainWindow(leitstelle);
        dialog.pack();
        dialog.setVisible(true);
        System.out.println(0); //für Debugging zum anhalten nach einem Programmdurchlauf
    }

}