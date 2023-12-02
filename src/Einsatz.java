import java.util.ArrayList;

public class Einsatz {
    private int einsatzNummer;
    private EinsatzArt einsatzArt;
    private int benoetigteFeuerwehrleute;
    private int benoetigteMtf;
    private int benoetigteElw;
    private int benoetigteTlf;
    private int benoetigteDlk;

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
            case NATURKATASTROPHE -> {
                setEinsatz(55,3,3,3,2);
                break;
            }
            case INDUSTRIEUNFALL -> {
                setEinsatz(40,3,2,2,2);
            }


        }
    }
    private void setEinsatz(int benoetigteFeuerwehrleute, int benoetigteElw, int benoetigteTlf, int benoetigteMtf, int benoetigteDlk) {
        this.benoetigteFeuerwehrleute = benoetigteFeuerwehrleute;
        this.benoetigteElw = benoetigteElw;
        this.benoetigteTlf = benoetigteTlf;
        this.benoetigteDlk = benoetigteDlk;
        this.benoetigteMtf = benoetigteMtf;
    }
    public int getEinsatzNummer(){
        return einsatzNummer;
    }

    public int getBenoetigteFeuerwehrleute() {
        return benoetigteFeuerwehrleute;
    }
    public void bedieneFeuerwehrmann(int bediente){
        benoetigteFeuerwehrleute-=bediente;
    }

    public int getBenoetigteMtf() {
        return benoetigteMtf;
    }
    public void bedieneMTF(int bediente){
        benoetigteMtf-=bediente;
    }

    public int getBenoetigteElw() {
        return benoetigteElw;
    }
    public void bedieneElw(int bediente){
        benoetigteElw-=bediente;
    }

    public int getBenoetigteTlf() {
        return benoetigteTlf;
    }
    public void bedieneTlf(int bediente){
        benoetigteTlf-=bediente;
    }

    public int getBenoetigteDlk() {
        return benoetigteDlk;
    }
    public void bedieneDlk(int bediente){
        benoetigteDlk-=bediente;
    }

    public EinsatzArt getEinsatzArt() {
        return einsatzArt;
    }

    public String getSonderatribute(){
        StringBuilder sonderatribute = new StringBuilder();
        sonderatribute.append(einsatzArt.toString() + "\n");
        int i = 1;
        for (Fahrzeug f: fahrzeuge) {
            sonderatribute.append(String.valueOf(i)+": ");
            i++;
            if (f instanceof ELW) {
                switch (((ELW) f).getDienstgrad()){
                    case A_DIENST -> {
                        sonderatribute.append("ELW: A-Dienst\n");
                        break;
                    }
                    case B_DIENST -> {
                        sonderatribute.append("ELW: B-Dienst\n");
                        break;
                    }
                    case C_DIENST -> {
                        sonderatribute.append("ELW: C-Dienst\n");
                        break;
                    }
                    case D_DIENST -> {
                        sonderatribute.append("ELW: D-Dienst\n");
                        break;
                    }

                }
            }
            else if (f instanceof TLF) {
                sonderatribute.append("TLF: " + String.valueOf(((TLF) f).getTank()) + " Liter\n");
            }
            else if (f instanceof MTF) {
                sonderatribute.append("MTF: Baujahr: " + String.valueOf(((MTF) f).getBaujahr()) + "\n");
            }
            else if (f instanceof DLK) {
                sonderatribute.append("DLK: Anleiterhöhe: " + String.valueOf(((DLK) f).getHoehe()) + " Meter\n");
            }
        }
        return sonderatribute.toString();
    }

    public void fahrzeugeEinbeziehen(Fahrzeug fahrzeug){
        fahrzeuge.add(fahrzeug);
    }

    public ArrayList<Fahrzeug> einsatzEnde(){
        ArrayList<Fahrzeug> rueckfahrt = new ArrayList<Fahrzeug>(fahrzeuge);
        fahrzeuge.clear();
        return rueckfahrt;
    }
}
