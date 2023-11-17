import java.util.ArrayList;

public class ELW/*Einsatzleitwagen*/ extends Fahrzeug {

    private Dienstgrad dienstgrad;

    public ELW(int fahrzeugnummer,Status status) {
        super(fahrzeugnummer,status, FahrzeugArt.PKW, 2);

    }



}
