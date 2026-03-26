package org.acme.exception;

public class BiblioteckaException extends Exception {

    public BiblioteckaException(String message) {
        super(message);
    }

    public BiblioteckaException(String message, Throwable cause) {
        super(message, cause);
    }
}
