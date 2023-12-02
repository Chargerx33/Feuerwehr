import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

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
    public String statusDerWache(){
        StringBuilder status = new StringBuilder();
        status.append("Verfügbare LKW fahrer: " + personalLkwFahrer.size() + "\n");
        status.append("Verfügbare Feurerwehrmänner: " + (personalPkwFahrer.size() + personalLkwFahrer.size()) + "(Inkl. LKW fahrer)\n" );
        int[] verfuegbareFahrzeuge = verfuegbareFahrzeuge();
        status.append("Verfügbare ELW: " + verfuegbareFahrzeuge[0] + "\n");
        status.append("Verfügbaer TLF: " + verfuegbareFahrzeuge[1] + "\n");
        status.append("Verfügbare DLK: " + verfuegbareFahrzeuge[2] + "\n");
        status.append("Verfügbare MTF: " + verfuegbareFahrzeuge[3] + "\n");
        status.append("Noch mögliche Einsätze: ");
        for(EinsatzArt einsatzArt: moeglicheEinsatzArten()){
            status.append(einsatzArt.toString() + ", ");
        }

        System.out.println(status.toString());
        System.out.println(status);
        return status.toString();
    }
    public int[] verfuegbareFahrzeuge() {
        int[]verfuegbar = new int[4];
        verfuegbar[0] = verfuegbar[1] = verfuegbar[2] = verfuegbar[3] = 0;
        for (Fahrzeug fahrzeug: fahrzeughalle) {
            switch (fahrzeug.getFahrzeugKategorie()){
                case ELW -> {
                    verfuegbar[0]++;
                }
                case TLF -> {
                    verfuegbar[1]++;
                }
                case DLK -> {
                    verfuegbar[2]++;
                }
                case MTF -> {
                    verfuegbar[3]++;
                }
            }
        }
        return verfuegbar;
    }
    public ArrayList<EinsatzArt> moeglicheEinsatzArten(){
        ArrayList<EinsatzArt> arten = new ArrayList<EinsatzArt>();
        int[] verfuegbareFahrzeuge = verfuegbareFahrzeuge();
        arten.add(EinsatzArt.WOHNUNGSBRAND);
        arten.add(EinsatzArt.VERKEHRSUNFALL);
        arten.add(EinsatzArt.NATURKATASTROPHE);
        arten.add(EinsatzArt.INDUSTRIEUNFALL);

        for (EinsatzArt einsatzArt: arten){
            Einsatz einsatz = new Einsatz(-1,einsatzArt);
            int benoetigteLkwFahrer = einsatz.getBenoetigteMtf()+einsatz.getBenoetigteDlk()+einsatz.getBenoetigteTlf();
            if (!(
                    //Prüfung, ob genug Fahrzeuge vorhanden sind
                    (
                        einsatz.getBenoetigteElw()<=verfuegbareFahrzeuge[0] &&
                        einsatz.getBenoetigteTlf()<=verfuegbareFahrzeuge[1] &&
                        einsatz.getBenoetigteTlf()<=verfuegbareFahrzeuge[2] &&
                        einsatz.getBenoetigteMtf()<=verfuegbareFahrzeuge[3]
                    ) &&
                    //Prüfung, ob genug LKW fahrer vorhanden sind
                    (benoetigteLkwFahrer<=personalLkwFahrer.size()) &&
                    //Prüfung, ob genug besatzung vorhanden
                    (einsatz.getBenoetigteFeuerwehrleute()<(personalPkwFahrer.size()+personalLkwFahrer.size()))
            )
            ){
                arten.remove(einsatzArt);
            }
        }
        return arten;
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
    public ArrayList<Fahrzeug> getFahrzeugeInWartungshalle() {
        ArrayList<Fahrzeug> fahrzeuge = new ArrayList<Fahrzeug>();
        fahrzeuge.addAll(wartungshalle);
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

    public void fahreInFahrzeughalle(Fahrzeug fahrzeug) {
        wartungshalle.remove(fahrzeug);
        fahrzeughalle.add(fahrzeug);
        sortiereHallen();
    }

    public void fahreInWartungshalle(Fahrzeug fahrzeug) {
        fahrzeughalle.remove(fahrzeug);
        wartungshalle.add(fahrzeug);
        sortiereHallen();
    }
    private void sortiereHallen(){
        //https://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
        //Hab das hier her, keine ahnung wieso das funktioniert, aber es funktioniert
        fahrzeughalle.sort((o1, o2) -> o1.getFahrzeugnummer().compareTo(o2.getFahrzeugnummer()));
        fahrzeughalle.sort(((o1, o2) -> o1.getFahrzeugKategorie().compareTo(o2.getFahrzeugKategorie())));
        wartungshalle.sort((o1, o2) -> o1.getFahrzeugnummer().compareTo(o2.getFahrzeugnummer()));
        wartungshalle.sort(((o1, o2) -> o1.getFahrzeugKategorie().compareTo(o2.getFahrzeugKategorie())));
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

    /**
     * @param fahrzeugKategorie gibt an, welche art von vahrzeug aus der Fahrzeighalle geholt werden soll
     * @return Gibt ein als passend erkanntes Objekt vom typ Fahrzeug zurück
     */
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

    /**
     * @param angekommeneFahrzeuge erhält eine Fahrzeugliste
     */
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