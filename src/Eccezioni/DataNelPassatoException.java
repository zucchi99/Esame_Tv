package Eccezioni;

import java.time.LocalDateTime;

public class DataNelPassatoException extends RuntimeException {

    static final String errorMessage = "data e ora devono non possono essere al passato";

    public DataNelPassatoException() {
        super();
    }

    public DataNelPassatoException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
