import java.util.ArrayList;

public class Fahrzeug {

    protected int fahrzeugNummer;
    protected FahrzeugKategorie fahrzeugKategorie;
    protected Status status;
    protected FahrzeugArt typ;
    protected int sitze;

    private ArrayList<Feuerwehrmann> besatzung = new ArrayList<Feuerwehrmann>(sitze);


    public Fahrzeug() {
        this.status = Status.ZWEI;
        this.sitze = 0;
        this.typ = FahrzeugArt.UNDEFINED;
    }

    public Fahrzeug(int fahrzeugNummer,FahrzeugKategorie fahrzeugKategorie, FahrzeugArt typ, int sitze) {
        this.fahrzeugNummer = fahrzeugNummer;
        this.fahrzeugKategorie = fahrzeugKategorie;
        this.status = status;
        this.typ = typ;
        this.sitze = sitze;

    }
    public int getSitze(){
        return sitze;
    }

    public FahrzeugArt getTyp() {
        return typ;
    }
    public Integer getFahrzeugnummer(){
        return fahrzeugNummer;
    }
    public FahrzeugKategorie getFahrzeugKategorie(){
        return fahrzeugKategorie;
    }

    public void aufsitzen(ArrayList<Feuerwehrmann> besatzung) {
        this.besatzung = besatzung;
    }

    public ArrayList<Feuerwehrmann> absitzen() {
        ArrayList<Feuerwehrmann> rueckfahrendeBesatzung = new ArrayList<Feuerwehrmann>();
        rueckfahrendeBesatzung.addAll(besatzung);
        besatzung.clear();
        return rueckfahrendeBesatzung;
    }

}