package org.acme.exception;

public class PozajmicaException extends BiblioteckaException {

    public PozajmicaException(String message) {
        super(message);
    }

    public PozajmicaException(String message, Throwable cause) {
        super(message, cause);
    }
}
