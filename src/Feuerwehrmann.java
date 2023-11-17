import java.util.Random;

public class Feuerwehrmann {
    private int personalnummer;

    private Dienstgrad dienstgrad;

    public Feuerwehrmann(int personalnummer){
        Random random = new Random();
        Dienstgrad[] possibleDienstgrad = {Dienstgrad.A_DIENST,Dienstgrad.B_DIENST,Dienstgrad.C_DIENST,Dienstgrad.D_DIENST};
        this.personalnummer = personalnummer;
        this.dienstgrad = possibleDienstgrad[random.nextInt(4)];
    }

    public int getPersonalnummer() {
        return personalnummer;
    }
    public Dienstgrad getDienstgrad() {
        return dienstgrad;
    }
    public void setDienstgrad(Dienstgrad dienstgrad) {
        this.dienstgrad = dienstgrad;
    }
}
