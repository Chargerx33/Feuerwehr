import java.util.Random;

public class Feuerwehrmann {
    private boolean lkwFahrer;
    private int personalnummer;

    private Dienstgrad dienstgrad;

    public Feuerwehrmann(boolean lkwFahrer, int personalnummer){
        Random random = new Random();
        Dienstgrad[] possibleDienstgrad = {Dienstgrad.A_DIENST,Dienstgrad.B_DIENST,Dienstgrad.C_DIENST,Dienstgrad.D_DIENST};
        this.lkwFahrer = lkwFahrer;
        this.personalnummer = personalnummer;
        this.dienstgrad = possibleDienstgrad[random.nextInt(4)];
    }
    public boolean isLkwFahrer() {
        return lkwFahrer;
    }

    public int getPersonalnummer() {
        return personalnummer;
    }

    public void setPersonalnummer(int personalnummer) {
        this.personalnummer = personalnummer;
    }

    public Dienstgrad getDienstgrad() {
        return dienstgrad;
    }

    public void setDienstgrad(Dienstgrad dienstgrad) {
        this.dienstgrad = dienstgrad;
    }
}
