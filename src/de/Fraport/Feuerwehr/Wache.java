package de.Fraport.Feuerwehr;

import de.Fraport.Feuerwehr.Enumerations.Einsatzart;
import de.Fraport.Feuerwehr.Enumerations.Fahrzeugkategorie;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Die Klasse Wache repräsentiert eine Feuerwache mit verschiedenen Ressourcen<p>
 * wie einer Fahrzeughalle, einer Wartungshalle und Feuerwehrleuten, welche in LKW-Fahrer und Pkw-Fahrer aufgeteilt werden<p>
 * Fahrzeuge, welche nicht an anderer Stelle im Programm verwendet werden, dennoch nicht verwendet werden, werden in wartungshalle gespeichert<p>
 * Feuerwehrleute, welche krank oder im Urlaub sind, werden verallgemeinert als Feuerwehrmann in der jeweiligen ArrayListe gespeichert
 */
public class Wache {
    private ArrayList<Fahrzeug> fahrzeughalle = new ArrayList<Fahrzeug>();
    private ArrayList<Fahrzeug> wartungshalle = new ArrayList<Fahrzeug>();
    private ArrayList<PkwFahrer> personalPkwFahrer = new ArrayList<PkwFahrer>();
    private ArrayList<LkwFahrer> personalLkwFahrer = new ArrayList<LkwFahrer>();

    private ArrayList<Feuerwehrmann> krank = new ArrayList<Feuerwehrmann>();
    private ArrayList<Feuerwehrmann> urlaub = new ArrayList<Feuerwehrmann>();

    /**
     * Fügt ein Fahrzeug zur Fahrzeughalle hinzu.<p>
     * darf nur zum Hinzufügen neuer fahrzeuge genutzt werden verwendet werden.
     * @param fahrzeug Das hinzuzufügende Fahrzeug.
     */
    public void newFahrzeug(Fahrzeug fahrzeug) {
        fahrzeughalle.add(fahrzeug);
    }

    /**
     * Fügt einen Feuerwehrmann zur verfügbaren Besatzung hinzu, je nach Qualifikation (PkwFahrer oder LkwFahrer) wird der Feuerwehrmann in die Jeweilige liste gespeichert.<p>
     * darf nur zum Hinzufügen neuer Feuerwehrleute genutzt werden
     * @param feuerwehrmann Der hinzuzufügende Feuerwehrmann.
     */
    public void newPersonal(Feuerwehrmann feuerwehrmann) {
        if (feuerwehrmann instanceof LkwFahrer) {
            personalLkwFahrer.add((LkwFahrer) feuerwehrmann);
        } else {
            personalPkwFahrer.add((PkwFahrer) feuerwehrmann);
        }
    }

    /**
     * Gibt den aktuellen Status der Wache aus, darunter verfügbare Fahrzeuge,
     * aktive Feuerwehrleute mit zusatz der verfügbaren LKW-Fahrer und mit den vorhandenen Mitteln noch bedienbare Einsätzarten.
     *
     * @return Der Status der Wache als String zur ausgabe in einem Userinterface.
     */
    public String statusDerWache(){
        StringBuilder status = new StringBuilder();
        status.append("Verfügbare LKW fahrer: " + personalLkwFahrer.size() + "\n");
        status.append("Verfügbare Feurerwehrmänner: " + (personalPkwFahrer.size() + personalLkwFahrer.size()) + " (Inkl. LKW fahrer)\n" );
        int[] verfuegbareFahrzeuge = verfuegbareFahrzeuge();
        status.append("Verfügbare ELW: " + verfuegbareFahrzeuge[0] + "\n");
        status.append("Verfügbaer TLF: " + verfuegbareFahrzeuge[1] + "\n");
        status.append("Verfügbare DLK: " + verfuegbareFahrzeuge[2] + "\n");
        status.append("Verfügbare MTF: " + verfuegbareFahrzeuge[3] + "\n");
        status.append("Noch mögliche Einsätze: ");
        for(Einsatzart einsatzArt: moeglicheEinsatzArten()){
            status.append(einsatzArt.toString() + ", ");
        }
        return status.toString();
    }

    /**
     * Ermittelt die Anzahl verfügbarer Fahrzeuge nach Kategorien.
     *
     * @return Ein Array vom Typ Integer mit der Anzahl verfügbarer Fahrzeuge pro Kategorie.
     * dabei Stellen die Indizes die Anzahl der folgenden Fahrzeugkategorie dar:<p>
     * [0] -> Verfügbare ELWs<p>
     * [1] -> Verfügbare TLFs<p>
     * [2] -> Verfügbare DLKs<p>
     * [3] -> Verfügbare MTFs
     */
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

    /**
     * Ermittelt die Einsatzarten, die von der Wache mit den noch verfügbaren Mitteln bewältigt werden können.
     *
     * @return Eine Liste der möglichen Einsatzarten.
     */
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

    /**
     * Sucht einen Feuerwehrmann in der aktiven Liste (personalLkwFahrer und personalPkwFahrer) basierend auf seiner Personalnummer.
     *
     * @param personalnummer Die Personalnummer des gesuchten Feuerwehrmanns.
     * @return Der gefundene Feuerwehrmann oder null, wenn kein Feuerwehrmann mit der angegebenen Personalnummer gefunden wurde.<p>
     * Es empfiehlt sich, durch prüfungen an anderer Stelle, zu vermeiden ein return von null zu erhalten.
     */
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

    /**
     * Sucht einen Feuerwehrmann in der Krankenliste (krank) basierend auf seiner Personalnummer.
     *
     * @param personalnummer Die Personalnummer des gesuchten Feuerwehrmanns.
     * @return Der gefundene Feuerwehrmann oder null, wenn kein Feuerwehrmann mit der angegebenen Personalnummer in der Krankenliste existiert.<p>
     * Es empfiehlt sich, durch prüfungen an anderer Stelle, zu vermeiden ein return von null zu erhalten.
     */
    public Feuerwehrmann getFromKrankByPersonalnummer(int personalnummer) {
        for (Feuerwehrmann feuerwehrmann : krank) {
            if (feuerwehrmann.getPersonalnummer() == personalnummer) {
                return (feuerwehrmann);
            }
        }
        System.err.println("Feuerwehrmann existiert nicht");
        return null;
    } //Kann NULL zurückgeben

    /**
     * Sucht einen Feuerwehrmann in der Urlaubsliste (urlaub) basierend auf seiner Personalnummer.
     *
     * @param personalnummer Die Personalnummer des gesuchten Feuerwehrmanns.
     * @return Der gefundene Feuerwehrmann oder null, wenn kein Feuerwehrmann mit der angegebenen Personalnummer in der Urlaubsliste existiert.<p>
     * Es empfiehlt sich, durch prüfungen an anderer Stelle, zu vermeiden ein return von null zu erhalten.
     */
    public Feuerwehrmann getFromUrlaubByPersonalnummer(int personalnummer) {

        for (Feuerwehrmann feuerwehrmann : urlaub) {
            if (feuerwehrmann.getPersonalnummer() == personalnummer) {
                return (feuerwehrmann);
            }
        }
        System.err.println("Feuerwehrmann existiert nicht");
        return null;
    } //Kann NULL zurückgeben

    /**
     * Gibt eine Liste aller Fahrzeuge in der Fahrzeughalle zurück.
     *
     * @return Eine ArrayList von Fahrzeugen, die sich in der Fahrzeughalle befinden.
     */
    public ArrayList<Fahrzeug> getFahrzeugeInFahrzeughalle() {
        ArrayList<Fahrzeug> fahrzeuge = new ArrayList<Fahrzeug>();
        fahrzeuge.addAll(fahrzeughalle);
        return fahrzeuge;
    }

    /**
     * Gibt eine Liste aller Fahrzeuge in der Wartungshalle zurück.
     *
     * @return Eine ArrayList von Fahrzeugen, die sich in der Wartungshalle befinden.
     */
    public ArrayList<Fahrzeug> getFahrzeugeInWartungshalle() {
        ArrayList<Fahrzeug> fahrzeuge = new ArrayList<Fahrzeug>();
        fahrzeuge.addAll(wartungshalle);
        return fahrzeuge;
    }

    /**
     * Gibt eine sortierte Liste vom Typ Integer der Personalnummern aller aktiven Feuerwehrleute zurück.
     *
     * @return Eine ArrayList vom Typ Integer, die die Personalnummern der aktiven Feuerwehrleute enthält.
     */
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

    /**
     * Gibt eine sortierte Liste vom Typ Integer der Personalnummern aller kranken Feuerwehrleute zurück.
     *
     * @return Eine ArrayList vom Typ Integer, die die Personalnummern der kranken Feuerwehrleute enthält.
     */
    public ArrayList<Integer> getKrankPersonalnummern() {
        ArrayList<Integer> personalnummern = new ArrayList<Integer>();
        for (Feuerwehrmann f : krank) {
            personalnummern.add(f.getPersonalnummer());
        }
        Collections.sort(personalnummern);
        return personalnummern;
    }

    /**
     * Gibt eine sortierte Liste vom Typ Integer der Personalnummern aller Feuerwehrleute zurück, die im Urlaub sind.
     *
     * @return Eine ArrayList vom Typ Integer, die die Personalnummern der Feuerwehrleute enthält, die im Urlaub sind.
     */
    public ArrayList<Integer> getUrlaubPersonalnummern() {
        ArrayList<Integer> personalnummern = new ArrayList<Integer>();
        for (Feuerwehrmann f : urlaub) {
            personalnummern.add(f.getPersonalnummer());
        }
        Collections.sort(personalnummern);
        return personalnummern;
    }

    /**
     * Verlegt ein Fahrzeug von der Wartungshalle in die Fahrzeughalle
     * und ruft folgend eine Funktion zur Sortierung beider Hallen auf
     *
     * @param fahrzeug Das zu verlegende Fahrzeug.
     */
    public void fahreInFahrzeughalle(Fahrzeug fahrzeug) {
        wartungshalle.remove(fahrzeug);
        fahrzeughalle.add(fahrzeug);
        sortiereHallen();
    }

    /**
     * Verlegt ein Fahrzeug von der Fahrzeughalle in die Wartungshalle
     * und ruft folgend eine Funktion zur Sortierung beider Hallen auf
     *
     * @param fahrzeug Das zu verlegende Fahrzeug.
     */
    public void fahreInWartungshalle(Fahrzeug fahrzeug) {
        fahrzeughalle.remove(fahrzeug);
        wartungshalle.add(fahrzeug);
        sortiereHallen();
    }

    /**
     * Sortiert die Fahrzeughalle und die Wartungshalle erst nach Fahrzeugnummer und dann nach Kategorie.
     */
    private void sortiereHallen(){
        //https://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
        //Hab das hier her, keine ahnung wieso das funktioniert, aber es funktioniert
        fahrzeughalle.sort((o1, o2) -> o1.getFahrzeugnummer().compareTo(o2.getFahrzeugnummer()));
        fahrzeughalle.sort(((o1, o2) -> o1.getFahrzeugKategorie().compareTo(o2.getFahrzeugKategorie())));
        wartungshalle.sort((o1, o2) -> o1.getFahrzeugnummer().compareTo(o2.getFahrzeugnummer()));
        wartungshalle.sort(((o1, o2) -> o1.getFahrzeugKategorie().compareTo(o2.getFahrzeugKategorie())));
    }

    /**
     * Fügt einen Feuerwehrmann zur aktiven Besatzung hinzu, je nach Typ (PkwFahrer oder LkwFahrer) wird der Feuerwehrmann auf der jeweiligen Liste gespeichert.
     *
     * @param feuerwehrmann Der hinzuzufügende Feuerwehrmann.
     */
    private void addToActive(Feuerwehrmann feuerwehrmann) {
        if (feuerwehrmann instanceof LkwFahrer) {
            personalLkwFahrer.add((LkwFahrer) feuerwehrmann);
        } else {
            personalPkwFahrer.add((PkwFahrer) feuerwehrmann);
        }
    }

    /**
     * Entfernt einen Feuerwehrmann aus der aktiven Besatzung, je nach Typ (PkwFahrer oder LkwFahrer) wird die Löschung auf der zugehörigen Liste gelöscht.
     *
     * @param feuerwehrmann Der zu entfernende Feuerwehrmann.
     */
    private void removeFromActive(Feuerwehrmann feuerwehrmann) {
        if (feuerwehrmann instanceof LkwFahrer) {
            personalLkwFahrer.remove(feuerwehrmann);
        } else {
            personalPkwFahrer.remove(feuerwehrmann);
        }
    }

    /**
     * Fügt einen Feuerwehrmann zur Liste der Erkrankten hinzu.
     *
     * @param feuerwehrmann Der hinzuzufügende Feuerwehrmann.
     */
    private void addToKrank(Feuerwehrmann feuerwehrmann) {
        krank.add(feuerwehrmann);
    }

    /**
     * Entfernt einen Feuerwehrmann aus der Liste der Erkrankten.
     *
     * @param feuerwehrmann Der zu entfernende Feuerwehrmann.
     */
    private void removeFromKrank(Feuerwehrmann feuerwehrmann) {
        krank.remove(feuerwehrmann);
    }

    /**
     * Fügt einen Feuerwehrmann zur Liste der Personen im Urlaub hinzu.
     *
     * @param feuerwehrmann Der hinzuzufügende Feuerwehrmann.
     */
    private void addToUrlaub(Feuerwehrmann feuerwehrmann) {
        urlaub.add(feuerwehrmann);
    }

    /**
     * Entfernt einen Feuerwehrmann aus der Liste der Personen im Urlaub.
     *
     * @param feuerwehrmann Der zu entfernende Feuerwehrmann.
     */
    private void removeFromUrlaub(Feuerwehrmann feuerwehrmann) {
        urlaub.remove(feuerwehrmann);
    }

    /**
     * Setzt einen Feuerwehrmann auf krank, indem der Feuerwehrmann von der aktiven Besatzung auf die Krankenliste verschoben wird.
     *
     * @param personalnummer Die Personalnummer des Feuerwehrmanns.
     */
    public void makeKrank(int personalnummer) {
        Feuerwehrmann f = getFromActiveByPersonalnummer(personalnummer);
        removeFromActive(f);
        addToKrank(f);
    }

    /**
     * Meldet einen Feuerwehrmann als wieder Gesund, indem der Feuerwehrmann von der Krankenliste in die aktive Besatzung verschoben wird.
     *
     * @param personalnummer Die Personalnummer des Feuerwehrmanns.
     */
    public void makeGesund(int personalnummer) {
        Feuerwehrmann f = getFromKrankByPersonalnummer(personalnummer);
        removeFromKrank(f);
        addToActive(f);
    }

    /**
     * Versetzt den Feuerwehrmann mit der angegebenen Personalnummer in den Urlaubsstatus, indem er aus der Liste der aktiven Feuerwehrleute entfernt und zur Urlaubsliste hinzugefügt wird.
     *
     * @param personalnummer Die Personalnummer des Feuerwehrmanns, der in den Urlaubsstatus versetzt werden soll.
     */
    public void toUrlaub(int personalnummer) {
        Feuerwehrmann f = getFromActiveByPersonalnummer(personalnummer);
        removeFromActive(f);
        addToUrlaub(f);
    }

    /**
     * Holt den Feuerwehrmann mit der angegebenen Personalnummer aus dem Urlaubsstatus zurück, indem er aus der Urlaubsliste entfernt und zur Liste der aktiven Feuerwehrleute hinzugefügt wird .
     *
     * @param personalnummer Die Personalnummer des Feuerwehrmanns, der aus dem Urlaubsstatus zurückgeholt werden soll.
     */
    public void fromUrlaub(int personalnummer) {
        Feuerwehrmann f = getFromUrlaubByPersonalnummer(personalnummer);
        removeFromUrlaub(f);
        addToActive(f);
    }

    /**
     * Generiert eine Fahrzeugbesatzung für einen Einsatz.
     *
     * @param anzahlPersonen Die Anzahl der Feuerwehrleute, die in der Besatzung benötigt werden.
     * @param lkw            Gibt an, ob ein Lkw-Fahrer in der Besatzung enthalten sein muss.
     * @return Eine ArrayList von Feuerwehrleuten, die die generierte Besatzung repräsentiert.<p>
     *         Kann null zurückgeben, wenn nicht genügend Personal vorhanden ist.<p>
     *         Ein rückgabewert von null sollte durch Prüfungen an anderer Stelle verhindert werden
     */
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
     * Holt ein Fahrzeug aus der Fahrzeughalle für einen Einsatz basierend auf der Fahrzeugkategorie.
     *
     * @param fahrzeugKategorie Die Fahrzeugkategorie, für die ein Fahrzeug benötigt wird.
     * @return Ein passendes Fahrzeug-Objekt, das aus der Fahrzeughalle geholt wurde.<p>
     *         Kann null zurückgeben, wenn die Sonne zwischen Mond und Erde steht ;).<p>
     *         Die Rückgabe von null kann in der Theorie nicht stattfinden, da alle möglichen Werte der übergebenen Fahrzeugkategorie mit einem eigenen return enden, wird jedoch vom Compiler verlangt >:(.
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
     * Registriert die Rückkehr von Fahrzeugen und deren Besatzung zur Wache und sortiert diese in die jeweiligen Listen ein.<p>
     * Anschließend werden Fahrzeughalle und Wartungshalle sortiert.
     *
     * @param angekommeneFahrzeuge Die Liste von Fahrzeugen, die von einem Einsatz zurückgekehrt sind.
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
        sortiereHallen();
    }
}