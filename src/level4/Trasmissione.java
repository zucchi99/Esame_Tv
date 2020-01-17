package level4;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.time.Duration;

public class Trasmissione {

    /*

    STATO ASTRATTO:
        rappresenta una trasmissione, dotata di:
        titolo, genere, info, varie lingue, vari sottotitoli (uno per lingua), data ora e durata messa in onda

    STATO CONCRETO:
        la trasmissione è un insieme di campi modificabili.
        Per i sottotitoli si usa un HashMap:
            - la chiave della mappa è una lingua (solo se esistente tra lista delle lingue)
            - il valore della mappa sono i sottotitoli, String. è ammesso solo una stringa di sottotitoli per lingua


    INVARIANTE:
        List<lingue> NOT NULL
        lingue[0] lingua di default

    TODO:
        creare classe dateTimeIsInPastException("data e ora require to be after actual time")

     */

    String titolo;
    Genere genere;
    String informazioni;
    List<String> lingue; //era meglio usare Set
    HashMap<String, String> sottotitoli;
    LocalDateTime dataEOra;
    Duration durata;

    /**
     * crea una nuova trasmissione
     * @param titolo titolo della trasmissione REQUIRE NOT NULL
     * @param genere genere della trasmissione
     * @param informazioni informazione sulla trasmissione
     * @param lingue insieme delle lingue disponibili
     * @param linguaDefault lingua di default REQUIRE NOT NULL
     * @param dataEOra data e ora della messas in onda REQUIRE NOT NULL and AFTER ACTUAL TIME
     * @param durata durata della trasmissione REQUIRE NOT NULL
     * @throws NullPointerException se uno dei parametri REQUIRE NOT NULL è NULL
     */
    public Trasmissione(
            String titolo, Genere genere, String informazioni, List<String> lingue, String linguaDefault,
            LocalDateTime dataEOra, Duration durata)
            throws NullPointerException, IllegalArgumentException {

        paramNotNull(titolo, linguaDefault, dataEOra, durata);
        if(dataEOra.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data e ora devono essere successive a ora");
        }
        this.titolo = titolo;
        this.genere = genere;
        this.informazioni = informazioni;
        this.lingue = lingue;
        setLinguaDefault(linguaDefault);
        this.dataEOra = dataEOra;
        this.durata = durata;
    }

    public String getTitolo() {
        return titolo;
    }

    /**
     * @param titolo REQUIRE NOT NULL
     * @throws NullPointerException
     */
    public void setTitolo(String titolo) throws NullPointerException {
        titolo = Objects.requireNonNull(titolo);
        this.titolo = titolo;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public String getInformazioni() {
        return informazioni;
    }

    public void setInformazioni(String informazioni) {
        this.informazioni = informazioni;
    }

    public List<String> getLingue() {
        return lingue;
    }

    /**
     * cambia le lingue della trasmissione
     * ATTENZIONE: la prima lingua nella lista diventa quella di default
     * @param lingue REQUIRE NOT NULL
     * @throws NullPointerException se lingue è NULL
     */
    public void setLingue(List<String> lingue)
            throws NullPointerException {
        lingue = Objects.requireNonNull(lingue);
        this.lingue = lingue;
    }

    /**
     * imposta come lingua di default quella nel parametro
     * la lingua viene aggiunta alle lingue della trasmissione se non era presente
     * @param lingua
     */
    public void setLinguaDefault(String lingua) {

            //lingue is null
        if (lingue == null) {
            lingue.add(lingua);
        } else if (lingue.contains(lingua)) {
            //la lingua è già presente tra le lingue
            int i = lingue.indexOf(lingua);
            swapLanguages(0, i);
        } else {
            //lingue non è null ma lingua non è presente tra lingue
            lingue.add(lingua);
            swapLanguages(0, lingue.size() - 1);
        }
    }

    public void swapLanguages(int i, int j) {
        String temp = lingue.get(i);
        lingue.set(i, lingue.get(j));
        lingue.set(j, temp);
    }

    public HashMap<String, String> getSottotitoli() {
        return sottotitoli;
    }

    /**
     * aggiunge sottotitoli a una certa lingua.
     * per una lingua è possibile avere solo una stringa di sottotitoli
     * @param lingua la lingua dei sottotitoli. deve essere già presente nella lista delle lingue REQUIRE NOT NULL
     * @param sottotitoli i sottotitoli nella citata lingua REQUIRE NOT NULL
     * @throws IllegalArgumentException se lingua non presente tra lingue o se esistono già sottotitoli in tale lingua
     * @throws NullPointerException se lingua o sottotitoli sono null
     */
    public void setSottotitoli(String lingua, String sottotitoli)
            throws IllegalArgumentException, NullPointerException {

        lingua = Objects.requireNonNull(lingua);
        sottotitoli = Objects.requireNonNull(sottotitoli);
        if(! lingue.contains(lingua)) {
            throw new IllegalArgumentException(lingua + ": lingua non esistente nella trasmissione");
        } else if(this.sottotitoli.containsKey(lingua)) {
            throw new IllegalArgumentException("esiste già un sottotitolo con questa lingua: " + lingua);
        } else {
            this.sottotitoli.put(lingua, sottotitoli);
        }
    }

    public LocalDateTime getDataEOraInizio() {
        return dataEOra;
    }

    /**
     * set data e ora della trasmissione
     * @param dataEOra REQUIRE NOT NULL AND AFTER ACTUAL TIME (no in past)
     * @throws NullPointerException se dataEOra is NULL
     * @throws IllegalArgumentException se dataEOra is before LocalDateTime.now()
     */
    public void setDataEOra(LocalDateTime dataEOra)
            throws NullPointerException, IllegalArgumentException {
        dataEOra = Objects.requireNonNull(dataEOra);
        if(dataEOra.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data e ora devono essere successive a ora");
        }
        this.dataEOra = dataEOra;
    }

    public Duration getDurata() {
        return durata;
    }

    public void setDurata(Duration durata) {
        this.durata = durata;
    }

    public LocalDateTime getDataEOraFine() {
        return dataEOra.plus(durata);
    }

    /**
     * restituisce true se object è una trasmissione e
     * se solo se le due trasmissioni hanno i seguenti parametri uguali:
     *  - titolo
     *  - ora inizio
     *  - durata
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {

        try {
            Trasmissione trasmissione = (Trasmissione)object;

            return trasmissione.getTitolo().equals(this.getTitolo()) &&
                    trasmissione.getDataEOraInizio().isEqual(dataEOra) &&
                    trasmissione.getDurata().equals(this.getDurata());

        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException("parametro non è una trasmissione");
        }
    }

    /**
     * foreach parameter check it is not null
     * it anyone is null throws exception
     * @param titolo titolo trasmissione REQUIRE NOT NULL
     * @param linguaDefault lingua di default REQUIRE NOT NULL
     * @param dataEOra data e ora della messa in onda REQUIRE NOT NULL
     * @param durata durata della trasmissione REQUIRE NOT NULL
     * @throws NullPointerException
     */
    public void paramNotNull ( String titolo, String linguaDefault, LocalDateTime dataEOra, Duration durata)
            throws NullPointerException {
        Objects.requireNonNull(titolo);
        Objects.requireNonNull(linguaDefault);
        Objects.requireNonNull(dataEOra);
        Objects.requireNonNull(durata);
    }

}
