package level3;
import Eccezioni.DataNelPassatoException;
import Eccezioni.TrasmissioneGiaPresenteException;

import Eccezioni.TrasmissioneNonEsistenteException;
import level4.Trasmissione;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Canale {

    /*
        STATO ASTRATTO:
            il canale contiene la sua programmazione, ovvero le sue trasmissioni

        STATO CONCRETO:
            il canale contiene una List<Trasmissione>
            possiede un nome, String

     */

    private String nome;
    List<Trasmissione> trasmissioni;

    public Canale(List<Trasmissione> trasmissioni) {
        this.trasmissioni = trasmissioni;
    }

    public Canale() {
        trasmissioni = new ArrayList<Trasmissione>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Trasmissione> getTrasmissioni() {
        return trasmissioni;
    }

    /**
     * aggiunge una trasmissione al canale se e solo se la trasmissione:
     *  - NOT NULL
     *  - non esiste nessuna trasmissione che si sovrappone con l'orario della trasmissione
     *  - la trasmissione non ha una data al passato
     * @param trasmissione REQUIRE NOT NULL
     * @throws NullPointerException trasmissione is NULL
     * @throws DataNelPassatoException la data della trasmissione è nel passato
     * @throws TrasmissioneGiaPresenteException la trasmissione si sovrappone con un'altra
     */
    public void addTrasmissioni(Trasmissione trasmissione)
            throws NullPointerException, DataNelPassatoException, TrasmissioneGiaPresenteException  {
        trasmissione = Objects.requireNonNull(trasmissione);
        if(trasmissione.getDataEOraInizio().isAfter(LocalDateTime.now())) {
            trasmissioneGiaPresente(trasmissione);
            this.trasmissioni.add(trasmissione);
        } else {
            throw new DataNelPassatoException();
        }
    }

    /**
     * rimuove la trasmissione se e solo se è presente una trasmissione che rispetta i canoni di equals
     * NB se la trasmissione differisce per caratteri "secondari" come descrizione o genere verrà comunque cancellata
     * @param trasmissione REQUIRE NOT NULL
     */
    public void removeTrasmissione(Trasmissione trasmissione) {
        Objects.requireNonNull(trasmissione);
        int count = 0;
        for(Trasmissione tr: trasmissioni) {
            if(tr.equals(trasmissione)) {
                trasmissioni.remove(tr);
                count++;
            }
        }
        if(count == 0) {
            throw new TrasmissioneNonEsistenteException();
        }
        //count > 1 should be impossible: can't have two trasmissions at same time
        assert count <= 1;

        /*
        non eseguo riga sotto perchè trasmissioni portebbero differire solo per parametri superflui
            trasmissioni.remove(trasmissione);
        */
    }

    /**
     * controlla che non ci sia già alcuna trasmissione che andrà in onda
     * tra data e ora di inizio e fine della trasmissione
     * se esiste, throws exception
     * @param trasmissione REQUIRE NOT NULL
     */
    private void trasmissioneGiaPresente(Trasmissione trasmissione) {
        Objects.requireNonNull(trasmissione);
        trasmissioni.forEach(tr -> {
            LocalDateTime start = tr.getDataEOraInizio();
            LocalDateTime end = tr.getDataEOraFine();
            LocalDateTime startTrasm = trasmissione.getDataEOraInizio();
            LocalDateTime endTrasm = trasmissione.getDataEOraFine();
            boolean ok = (
                    (start.isBefore(startTrasm) && end.isBefore(endTrasm)) ||
                    (start.isAfter(startTrasm) && end.isAfter(endTrasm)));
            if(!ok) {
                throw new TrasmissioneGiaPresenteException();
            }
        } );
    }
    
    //per titolo, canale, giorno, ora)

    /**
     * cerca e restituisce tutte le trasmissioni future che
     * contengono nel titolo della trasmissione il titolo ricevuto parametro
     * @param titolo il titolo che si cerca
     * @return lista delle trasmissioni future con quel titolo
     * @throws NullPointerException se titolo è nullo o se non è stata ancora inserita nessuna trasmissione
     */
    public List<Trasmissione> cercaPerTitolo(String titolo)
            throws NullPointerException {
        Objects.requireNonNull(titolo);
        Objects.requireNonNull(trasmissioni);
        return trasmissioni.stream().filter(
                t -> t.getTitolo().contains(titolo) && t.TrasmissioneEFutura()).collect(Collectors.toList());
    }
    
}
