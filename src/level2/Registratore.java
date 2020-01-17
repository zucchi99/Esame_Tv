package level2;

import API.Trasmissione;
import Eccezioni.TrasmissioneGiaPresenteException;
import Eccezioni.TrasmissioneNonEsistenteException;
import java.util.List;
import java.util.stream.Collectors;

public class Registratore implements API.Registratore {

    /* INVARIANTE DI CLASSE
    *          Le trasmissioni sono tutte non nulle */

    List<Trasmissione> trasmissioniFuture;

    /**
     * Aggiunge una nuova trasmissione a quelle da registrare
     * @param tr la trasmissione da aggiugere. REQUIRED non nulla
     * @throws TrasmissioneGiaPresenteException se la trasmissione data è già presente all'interno di quelle future in registrazione
     */
    @Override
    public void aggiungiTrasmissione(Trasmissione tr) throws TrasmissioneGiaPresenteException {
        if (!controllaPrenotazioneTrasmissione(tr)) {
            trasmissioniFuture.add(tr);
        } else {
            throw new TrasmissioneGiaPresenteException("La trasmissione è già presente tra la lista di quelle future da registrare");
        }
    }

    /**
     * Controlla se la trasmissione è presente tra quelle future da registrare
     * @param tr è la trasmissione da controllare. REQUIRED non nulla
     * @return vero se la trasmissione appartiene a quelle future registrate
     */
    @Override
    public boolean controllaPrenotazioneTrasmissione(Trasmissione tr) {
        List<Trasmissione> disponibilità = trasmissioniFuture.stream().filter(x -> x.equals(tr)).collect(Collectors.toList());
        return !disponibilità.isEmpty();
    }

    /**
     * Elimina una trasmissione che appartiene alla lista delle trasmissioni future da registrare
     * @param tr p la trasmissione da eliminare. REQUIRED not nulla
     * @throws TrasmissioneNonEsistenteException se la trasmissione non è presente all'interno della lista delle future registrazioni
     */
    @Override
    public void eliminaTrasmissione(Trasmissione tr) throws TrasmissioneNonEsistenteException {
        if (controllaPrenotazioneTrasmissione(tr)) {
            trasmissioniFuture.remove(tr);
        } else {
            throw new TrasmissioneNonEsistenteException("La trasmissione non è presente nella lista delle registrazioni");
        }
    }
}
