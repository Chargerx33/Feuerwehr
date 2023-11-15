import java.util.ArrayList;

public class Fahrzeug {

    protected int fahrzeugNummer;
    protected Status status;
    protected FahrzeugArt typ;
    protected int sitze;

    private ArrayList<Feuerwehrmann> besatzung = new ArrayList<Feuerwehrmann>(sitze);


    public Fahrzeug() {
        this.status = Status.ZWEI;
        this.sitze = 0;
        this.typ = FahrzeugArt.UNDEFINED;
    }

    public Fahrzeug(int fahrzeugNummer, Status status, FahrzeugArt typ, int sitze) {
        this.fahrzeugNummer = fahrzeugNummer;
        this.status = status;
        this.typ = typ;
        this.sitze = sitze;

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public FahrzeugArt getTyp() {
        return typ;
    }

    public void setTyp(FahrzeugArt typ) {
        this.typ = typ;
    }

    public void aufsitzen(Feuerwehrmann feuerwehrmann) {
        besatzung.add(feuerwehrmann);
    }

    public void absitzen(Feuerwehrmann feuerwehrmann) {
        besatzung.remove(feuerwehrmann);
    }

    public void alleAbsitzen() {
        besatzung.clear();
    }
}