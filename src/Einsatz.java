import java.util.ArrayList;

public abstract class Einsatz {
    protected int einsatzNummer;
    protected int needFeuerwehrleute;
    protected int needMtf;
    protected int needElw;
    protected int needTlf;
    protected int needDlk;

    private ArrayList<Fahrzeug> fahrzeuge = new ArrayList<Fahrzeug>();

    public Einsatz(int einsatzNummer,
                   int needFeuerwehrleute,
                   int needElw,
                   int needTlf,
                   int needMtf,
                   int needDlk)
    {
        this.einsatzNummer = einsatzNummer;
        this.needFeuerwehrleute = needFeuerwehrleute;
        this.needElw = needElw;
        this.needTlf = needTlf;
        this.needMtf = needMtf;
        this.needDlk = needDlk;
    }
    public int getEinsatzNummer(){
        return einsatzNummer;
    }
}
