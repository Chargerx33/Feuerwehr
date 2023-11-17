import java.util.ArrayList;

public class Wache {
    private ArrayList<Fahrzeug> fahrzeughalle = new ArrayList<Fahrzeug>();
    private ArrayList<pkwFahrer> personal_pkwFahrer = new ArrayList<pkwFahrer>();
    private ArrayList<lkwFahrer> personal_lkwFahrer = new ArrayList<lkwFahrer>();

    private ArrayList<Feuerwehrmann> krank = new ArrayList<Feuerwehrmann>();
    private ArrayList<Feuerwehrmann> urlaub = new ArrayList<Feuerwehrmann>();
    public void addFahrzeug(Fahrzeug fahrzeug) {
        fahrzeughalle.add(fahrzeug);
    }
    public void addPersonal(Feuerwehrmann feuerwehrmann){
        if (feuerwehrmann instanceof lkwFahrer) {
            personal_lkwFahrer.add((lkwFahrer) feuerwehrmann);
        }
        else {
            personal_pkwFahrer.add((pkwFahrer) feuerwehrmann);
        }
    }

    public Feuerwehrmann getFromActiveByPersonalnummer(int personalnummer){

        for (Feuerwehrmann feuerwehrmann : personal_lkwFahrer) {
            if (feuerwehrmann.getPersonalnummer() == personalnummer) {
                return (feuerwehrmann);
            }
        }

        for (int i = 0; i < personal_pkwFahrer.size(); i++) {
            Feuerwehrmann feuerwehrmann = personal_lkwFahrer.get(i);
            if (feuerwehrmann.getPersonalnummer() == personalnummer) {
                return (feuerwehrmann);
            }

        }
        System.err.println("Feuerwehrmann existiert nicht");
        return null;
    } //Kann NULL zurückgeben
    public Feuerwehrmann getFromKrankByPersonalnummer(int personalnummer) {
        for (Feuerwehrmann feuerwehrmann : krank) {
            if (feuerwehrmann.getPersonalnummer() == personalnummer) {
                return (feuerwehrmann);
            }
        }
        System.err.println("Feuerwehrmann existiert nicht");
        return null;
    } //Kann NULL zurückgeben
    public Feuerwehrmann getFromUrlaubByPersonalnummer(int personalnummer) {

        for (Feuerwehrmann feuerwehrmann : urlaub) {
            if (feuerwehrmann.getPersonalnummer() == personalnummer) {
                return (feuerwehrmann);
            }
        }
        System.err.println("Feuerwehrmann existiert nicht");
        return null;
    } //Kann NULL zurückgeben
    public ArrayList<Integer> getActivePersonalnummern(){
        ArrayList<Integer> personalnummern = new ArrayList<Integer>();
        for (Feuerwehrmann f : personal_pkwFahrer) {
            personalnummern.add(f.getPersonalnummer());
        }
        for (Feuerwehrmann f : personal_lkwFahrer) {
            personalnummern.add(f.getPersonalnummer());
        }

        return personalnummern;
    }
    public ArrayList<Integer> getKrankPersonalnummern() {
        ArrayList<Integer> personalnummern = new ArrayList<Integer>();
        for (Feuerwehrmann f : krank) {
            personalnummern.add(f.getPersonalnummer());
        }
        return personalnummern;
    }
    public ArrayList<Integer> getUrlaubPersonalnummern() {
        ArrayList<Integer> personalnummern = new ArrayList<Integer>();
        for (Feuerwehrmann f : urlaub) {
            personalnummern.add(f.getPersonalnummer());
        }
        return personalnummern;
    }
    private void addToActive(Feuerwehrmann f) {
        if (f instanceof lkwFahrer) {
            personal_lkwFahrer.add((lkwFahrer) f);
        }
        else {
            personal_pkwFahrer.add((pkwFahrer) f);
        }
    }
    private void removeFromActive(Feuerwehrmann f){
        if(f instanceof lkwFahrer){
            personal_lkwFahrer.remove(f);
        }
        else {
            personal_pkwFahrer.remove(f);
        }
    }
    private void addToKrank(Feuerwehrmann f){
        krank.add(f);
    }
    private void removeFromKrank(Feuerwehrmann f){
        krank.remove(f);
    }
    private void addToUrlaub(Feuerwehrmann f){
        urlaub.add(f);
    }
    private void removeFromUrlaub(Feuerwehrmann f){
        urlaub.remove(f);
    }
    public void makeKrank(int personalnummer){
        Feuerwehrmann f = getFromActiveByPersonalnummer(personalnummer);
        removeFromActive(f);
        addToKrank(f);
    }
    public void makeGesund(int personalnummer){
        Feuerwehrmann f = getFromKrankByPersonalnummer(personalnummer);
        removeFromKrank(f);
        addToActive(f);
    }
    public void toUrlaub(int personalnummer){
        Feuerwehrmann f = getFromActiveByPersonalnummer(personalnummer);
        removeFromActive(f);
        addToUrlaub(f);
    }
    public void fromUrlaub(int personalnummer){
        Feuerwehrmann f = getFromUrlaubByPersonalnummer(personalnummer);
        removeFromUrlaub(f);
        addToActive(f);
    }

    public ArrayList<Feuerwehrmann> generateBesatzung(int anzahlPersonen, boolean lkw){
        ArrayList<Feuerwehrmann> besatzung = new ArrayList<Feuerwehrmann>();
        if (lkw) {
        Feuerwehrmann f = personal_lkwFahrer.getFirst();
        personal_lkwFahrer.remove(f);
        besatzung.add(f);
        anzahlPersonen--;
        }
        for (int i = 0; i<anzahlPersonen; i++){
            Feuerwehrmann f;
            if (!personal_pkwFahrer.isEmpty()) {
                 f = personal_pkwFahrer.getFirst();
                 removeFromActive(f);
                 besatzung.add(f);
            } else if (!personal_lkwFahrer.isEmpty()) {
                f = personal_lkwFahrer.getFirst();
                removeFromActive(f);
                besatzung.add(f);

            }
            else {
                return null;
            }
        }
        return besatzung;
    } //Kann NULL zurückgeben
}
