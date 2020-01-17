package API;

public interface Registratore {

    void aggiungiTrasmissione(Trasmissione tr);

    boolean controllaPrenotazioneTrasmissione(Trasmissione tr);

    void eliminaTrasmissione(Trasmissione tr);
}
