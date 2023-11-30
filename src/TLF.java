import java.util.ArrayList;
import java.util.Random;

public class TLF/*Tankloeschfahrzeug*/ extends Fahrzeug{
    private int tank;

    public TLF(int fahrzeufnummer,Status status) {
        super(fahrzeufnummer,FahrzeugKategorie.TLF,status, FahrzeugArt.LKW, 4);
        int[] moeglicheTanks = {3000,10000};
        Random rand = new Random();
        this.tank = moeglicheTanks[rand.nextInt(moeglicheTanks.length)];
    }
    public int getTank() {
        return tank;
    }

}
