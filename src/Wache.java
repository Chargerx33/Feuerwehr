import java.util.ArrayList;
import java.util.Collections;

public class Wache {
    private ArrayList<Fahrzeug> fahrzeughalle = new ArrayList<Fahrzeug>();
    private ArrayList<Fahrzeug> wartungshalle = new ArrayList<Fahrzeug>();
    private ArrayList<PkwFahrer> personalPkwFahrer = new ArrayList<PkwFahrer>();
    private ArrayList<LkwFahrer> personalLkwFahrer = new ArrayList<LkwFahrer>();

    private ArrayList<Feuerwehrmann> krank = new ArrayList<Feuerwehrmann>();
    private ArrayList<Feuerwehrmann> urlaub = new ArrayList<Feuerwehrmann>();

    public void addFahrzeug(Fahrzeug fahrzeug) {
        fahrzeughalle.add(fahrzeug);
    }

    public void addPersonal(Feuerwehrmann feuerwehrmann) {
        if (feuerwehrmann instanceof LkwFahrer) {
            personalLkwFahrer.add((LkwFahrer) feuerwehrmann);
        } else {
            personalPkwFahrer.add((PkwFahrer) feuerwehrmann);
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
        for(Einsatzart einsatzArt: moeglicheEinsatzArten()){
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
    public ArrayList<Einsatzart> moeglicheEinsatzArten(){
        ArrayList<Einsatzart> arten = new ArrayList<Einsatzart>();
        int[] verfuegbareFahrzeuge = verfuegbareFahrzeuge();
        arten.add(Einsatzart.WOHNUNGSBRAND);
        arten.add(Einsatzart.VERKEHRSUNFALL);
        arten.add(Einsatzart.NATURKATASTROPHE);
        arten.add(Einsatzart.INDUSTRIEUNFALL);
        ArrayList<Einsatzart> moeglich = (ArrayList<Einsatzart>) arten.clone();

        for (Einsatzart einsatzArt: arten){
            Einsatz einsatz = new Einsatz(-1,einsatzArt);
            int benoetigteLkwFahrer = einsatz.getBenoetigteMTF()+einsatz.getBenoetigteDLK()+einsatz.getBenoetigteTLF();
            if (!(
                    //Prüfung, ob genug Fahrzeuge vorhanden sind
                    (
                        einsatz.getBenoetigteELW()<=verfuegbareFahrzeuge[0] &&
                        einsatz.getBenoetigteTLF()<=verfuegbareFahrzeuge[1] &&
                        einsatz.getBenoetigteTLF()<=verfuegbareFahrzeuge[2] &&
                        einsatz.getBenoetigteMTF()<=verfuegbareFahrzeuge[3]
                    ) &&
                    //Prüfung, ob genug LKW fahrer vorhanden sind
                    (benoetigteLkwFahrer<=personalLkwFahrer.size()) &&
                    //Prüfung, ob genug besatzung vorhanden
                    (einsatz.getBenoetigteFeuerwehrleute()<(personalPkwFahrer.size()+personalLkwFahrer.size()))
            )
            ){
                moeglich.remove(einsatzArt);
            }
        }
        return moeglich;
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
        if (f instanceof LkwFahrer) {
            personalLkwFahrer.add((LkwFahrer) f);
        } else {
            personalPkwFahrer.add((PkwFahrer) f);
        }
    }

    private void removeFromActive(Feuerwehrmann f) {
        if (f instanceof LkwFahrer) {
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
    public Fahrzeug fahrzeugZuEinsatz(Fahrzeugkategorie fahrzeugKategorie) {
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