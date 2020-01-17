package level2;

import level4.Trasmissione;
import java.util.List;
import java.util.stream.Collectors;

public class Registratore implements tv.Registratore {
    List<Trasmissione> trasmissioniFuture;

    /**
     * Aggiunge una nuova trasmissione a quelle da registrare
     * @param tr la trasmissione da aggiugere. Required non nulla
     */
    @Override
    public void aggiungiTrasmissione(tv.Trasmissione tr) throws IllegalArgumentException {
        if (controllaPrenotazioneTrasmissione(tr)) {
            trasmissioniFuture.add((Trasmissione) tr);
        } else {
            throw new IllegalArgumentException("La trasmissione non è presente nella lista delle registrazioni");
        }
    }

    /**
     * Controlla se la trasmissione è presente tra quelle future da registrare
     * @param tr è la trasmissione da controllare. required non nulla
     * @return vero se la trasmissione appartiene a quelle future registrate
     */
    @Override
    public boolean controllaPrenotazioneTrasmissione(tv.Trasmissione tr) {
        List<Trasmissione> disponibilità = trasmissioniFuture.stream().filter(x -> x.equals(tr)).collect(Collectors.toList());
        return disponibilità.isEmpty();
    }

    @Override
    public void eliminaTrasmissione(tv.Trasmissione tr) throws IllegalArgumentException {
        if (controllaPrenotazioneTrasmissione(tr)) {
            trasmissioniFuture.remove(tr);
        } else {
            throw new IllegalArgumentException("La trasmissione non è presente nella lista delle registrazioni");
        }
    }
}
