import java.util.ArrayList;
import java.util.Collections;

public class Wache {
    private ArrayList<Fahrzeug> fahrzeughalle = new ArrayList<Fahrzeug>();
    private ArrayList<Fahrzeug> wartungshalle = new ArrayList<Fahrzeug>();
    private ArrayList<pkwFahrer> personalPkwFahrer = new ArrayList<pkwFahrer>();
    private ArrayList<lkwFahrer> personalLkwFahrer = new ArrayList<lkwFahrer>();

    private ArrayList<Feuerwehrmann> krank = new ArrayList<Feuerwehrmann>();
    private ArrayList<Feuerwehrmann> urlaub = new ArrayList<Feuerwehrmann>();

    public void addFahrzeug(Fahrzeug fahrzeug) {
        fahrzeughalle.add(fahrzeug);
    }

    public void addPersonal(Feuerwehrmann feuerwehrmann) {
        if (feuerwehrmann instanceof lkwFahrer) {
            personalLkwFahrer.add((lkwFahrer) feuerwehrmann);
        } else {
            personalPkwFahrer.add((pkwFahrer) feuerwehrmann);
        }
    }

    public Feuerwehrmann getFromActiveByPersonalnummer(int personalnummer) {

        for (Feuerwehrmann feuerwehrmann : personalLkwFahrer) {
            if (feuerwehrmann.getPersonalnummer() == personalnummer) {
                return (feuerwehrmann);
            }
        }

        for (int i = 0; i < personalPkwFahrer.size(); i++) {
            Feuerwehrmann feuerwehrmann = personalPkwFahrer.get(i);
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

    public ArrayList<Fahrzeug> getFahrzeugeInFahrzeughalle() {
        ArrayList<Fahrzeug> fahrzeuge = new ArrayList<Fahrzeug>();
        fahrzeuge.addAll(fahrzeughalle);
        return fahrzeuge;
    }

    public ArrayList<Integer> getActivePersonalnummern() {
        ArrayList<Integer> personalnummern = new ArrayList<Integer>();
        for (Feuerwehrmann f : personalPkwFahrer) {
            personalnummern.add(f.getPersonalnummer());
        }
        for (Feuerwehrmann f : personalLkwFahrer) {
            personalnummern.add(f.getPersonalnummer());
        }
        Collections.sort(personalnummern);
        return personalnummern;
    }

    public ArrayList<Integer> getKrankPersonalnummern() {
        ArrayList<Integer> personalnummern = new ArrayList<Integer>();
        for (Feuerwehrmann f : krank) {
            personalnummern.add(f.getPersonalnummer());
        }
        Collections.sort(personalnummern);
        return personalnummern;
    }

    public ArrayList<Integer> getUrlaubPersonalnummern() {
        ArrayList<Integer> personalnummern = new ArrayList<Integer>();
        for (Feuerwehrmann f : urlaub) {
            personalnummern.add(f.getPersonalnummer());
        }
        Collections.sort(personalnummern);
        return personalnummern;
    }

    public void driveToFahrzeughalle(int fahrzeugnummer, FahrzeugKategorie fahrzeugKategorie) {
        for (Fahrzeug fahrzeug : wartungshalle) {
            if (fahrzeug.fahrzeugNummer == fahrzeugnummer) {
                if (fahrzeug.fahrzeugKategorie == fahrzeugKategorie) {
                    wartungshalle.remove(fahrzeug);
                    fahrzeughalle.add(fahrzeug);
                }
            }
        }
    }

    public void driveToWartungshalle(int fahrzeugnummer, FahrzeugKategorie fahrzeugKategorie) {
        for (Fahrzeug fahrzeug : fahrzeughalle) {
            if (fahrzeug.fahrzeugNummer == fahrzeugnummer) {
                if (fahrzeug.fahrzeugKategorie == fahrzeugKategorie) {
                    fahrzeughalle.remove(fahrzeug);
                    wartungshalle.add(fahrzeug);
                }
            }
        }
    }

    private void addToActive(Feuerwehrmann f) {
        if (f instanceof lkwFahrer) {
            personalLkwFahrer.add((lkwFahrer) f);
        } else {
            personalPkwFahrer.add((pkwFahrer) f);
        }
    }

    private void removeFromActive(Feuerwehrmann f) {
        if (f instanceof lkwFahrer) {
            personalLkwFahrer.remove(f);
        } else {
            personalPkwFahrer.remove(f);
        }
    }

    private void addToKrank(Feuerwehrmann f) {
        krank.add(f);
    }

    private void removeFromKrank(Feuerwehrmann f) {
        krank.remove(f);
    }

    private void addToUrlaub(Feuerwehrmann f) {
        urlaub.add(f);
    }

    private void removeFromUrlaub(Feuerwehrmann f) {
        urlaub.remove(f);
    }

    public void makeKrank(int personalnummer) {
        Feuerwehrmann f = getFromActiveByPersonalnummer(personalnummer);
        removeFromActive(f);
        addToKrank(f);
    }

    public void makeGesund(int personalnummer) {
        Feuerwehrmann f = getFromKrankByPersonalnummer(personalnummer);
        removeFromKrank(f);
        addToActive(f);
    }

    public void toUrlaub(int personalnummer) {
        Feuerwehrmann f = getFromActiveByPersonalnummer(personalnummer);
        removeFromActive(f);
        addToUrlaub(f);
    }

    public void fromUrlaub(int personalnummer) {
        Feuerwehrmann f = getFromUrlaubByPersonalnummer(personalnummer);
        removeFromUrlaub(f);
        addToActive(f);
    }

    public ArrayList<Feuerwehrmann> generateBesatzung(int anzahlPersonen, boolean lkw) {
        ArrayList<Feuerwehrmann> besatzung = new ArrayList<Feuerwehrmann>();
        if (lkw) {
            Feuerwehrmann f = personalLkwFahrer.getFirst();
            personalLkwFahrer.remove(f);
            besatzung.add(f);
            anzahlPersonen--;
        }
        for (int i = 0; i < anzahlPersonen; i++) {
            Feuerwehrmann f;
            if (!personalPkwFahrer.isEmpty()) {
                f = personalPkwFahrer.getFirst();
                removeFromActive(f);
                besatzung.add(f);
            } else if (!personalLkwFahrer.isEmpty()) {
                f = personalLkwFahrer.getFirst();
                removeFromActive(f);
                besatzung.add(f);
            } else {
                return null;
            }
        }
        return besatzung;
    } //Kann NULL zurückgeben

    public Fahrzeug fahrzeugZuEinsatz(FahrzeugKategorie fahrzeugKategorie) {
        switch (fahrzeugKategorie) {
            case ELW -> {
                for (Fahrzeug f : fahrzeughalle) {
                    if (f instanceof ELW) {
                        fahrzeughalle.remove(f);
                        return f;
                    }
                }
            }
            case DLK ->{
                for (Fahrzeug f : fahrzeughalle){
                    if (f instanceof DLK){
                        fahrzeughalle.remove(f);
                        return f;
                    }
                }
            }
            case MTF -> {
                for (Fahrzeug f : fahrzeughalle){
                    if (f instanceof MTF){
                        fahrzeughalle.remove(f);
                        return f;
                    }
                }
            }
            case TLF -> {
                for (Fahrzeug f : fahrzeughalle){
                    if (f instanceof TLF){
                        fahrzeughalle.remove(f);
                        return f;
                    }
                }
            }
        }
        return null;
    }
    public void rueckkehr(ArrayList<Fahrzeug> angekommeneFahrzeuge){
        for (Fahrzeug fahrzeug:angekommeneFahrzeuge) {
            ArrayList<Feuerwehrmann> besatzung = fahrzeug.absitzen();
            while (besatzung.size()>0){
                addToActive(besatzung.getFirst());
                besatzung.remove(besatzung.getFirst());
            }
        }
        fahrzeughalle.addAll(angekommeneFahrzeuge);
    }
}