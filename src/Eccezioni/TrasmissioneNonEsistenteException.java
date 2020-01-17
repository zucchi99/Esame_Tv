package Eccezioni;

public class TrasmissioneNonEsistenteException extends RuntimeException {

    static final String errorMessage = "trasmissione non è presente della programmazione del canale";

    public TrasmissioneNonEsistenteException() {
        super();
    }

    public TrasmissioneNonEsistenteException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
