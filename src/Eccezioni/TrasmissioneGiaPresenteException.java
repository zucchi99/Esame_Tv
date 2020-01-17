package Eccezioni;

import java.time.LocalDateTime;

public class TrasmissioneGiaPresenteException extends RuntimeException {

    static final String errorMessage = "esiste già una trasmissione in questo arco di tempo ";

    public TrasmissioneGiaPresenteException() {
        super();
    }

    public TrasmissioneGiaPresenteException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

}
