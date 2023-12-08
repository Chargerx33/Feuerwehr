package de.Fraport.Feuerwehr.Easteregg;

import java.net.URI;

public class Easteregg {
    public Easteregg() {
        try {
            java.awt.Desktop.getDesktop().browse(URI.create("https://www.youtube.com/watch?v=tc-pRTk2r7k"));
        }catch (Exception e){
            System.out.println("Video ist down oder so");
        }
    }
}

