import java.util.ArrayList;

public class Einsatz {
    private int einsatzNummer;
    private EinsatzArt einsatzArt;
    private int needFeuerwehrleute;
    private int needMtf;
    private int needElw;
    private int needTlf;
    private int needDlk;

    private ArrayList<Fahrzeug> fahrzeuge = new ArrayList<Fahrzeug>();

    public Einsatz(int einsatzNummer, EinsatzArt einsatzArt)
    {
        this.einsatzNummer = einsatzNummer;
        this.einsatzArt = einsatzArt;
        switch (einsatzArt){
            case WOHNUNGSBRAND -> {
                setEinsatz(22,1,2,1,1);
                break;
            }
            case VERKEHRSUNFALL -> {
                setEinsatz(16,1,1,1,0);
                break;
            }
            case NATURKATHASTROPHE -> {
                setEinsatz(55,3,3,3,2);
                break;
            }
            case INDUSTRIEUNFALL -> {
                setEinsatz(40,3,2,2,2);
            }


        }
    }
    private void setEinsatz(int needFeuerwehrleute, int needElw, int needTlf, int needMtf, int needDlk) {
        this.needFeuerwehrleute = needFeuerwehrleute;
        this.needElw = needElw;
        this.needTlf = needTlf;
        this.needDlk = needDlk;
        this.needMtf = needMtf;
    }
    public int getEinsatzNummer(){
        return einsatzNummer;
    }

    public int getNeedFeuerwehrleute() {
        return needFeuerwehrleute;
    }

    public int getNeedMtf() {
        return needMtf;
    }

    public int getNeedElw() {
        return needElw;
    }

    public int getNeedTlf() {
        return needTlf;
    }

    public int getNeedDlk() {
        return needDlk;
    }

    public EinsatzArt getEinsatzArt() {
        return einsatzArt;
    }
}
