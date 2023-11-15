import java.util.ArrayList;

public class Einsatz {
    protected int needFeuerwehrleute;
    protected int needMtf;
    protected int needElw;
    protected int needTlf;
    protected int needDlk;

    private ArrayList<Fahrzeug> fahrzeuge = new ArrayList<Fahrzeug>();

    public Einsatz(int needFeuerwehrleute,
                   int needElw,
                   int needTlf,
                   int needMtf,
                   int needDlk)
    {
        this.needFeuerwehrleute = needFeuerwehrleute;
        this.needElw = needElw;
        this.needTlf = needTlf;
        this.needMtf = needMtf;
        this.needDlk = needDlk;
    }
}
