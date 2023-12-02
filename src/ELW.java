import java.util.ArrayList;

public class ELW/*Einsatzleitwagen*/ extends Fahrzeug {

    private Dienstgrad dienstgrad = Dienstgrad.D_DIENST;

    public ELW(int fahrzeugnummer) {
        super(fahrzeugnummer,FahrzeugKategorie.ELW, FahrzeugArt.PKW, 2);

    }

    public Dienstgrad getDienstgrad() {
        return dienstgrad;
    }

    public void setDienstgrad(Dienstgrad dienstgrad) {
        this.dienstgrad = dienstgrad;
    }
}
