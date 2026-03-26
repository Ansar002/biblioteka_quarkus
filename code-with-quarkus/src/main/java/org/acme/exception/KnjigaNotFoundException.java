package org.acme.exception;

public class KnjigaNotFoundException extends BiblioteckaException {

    public KnjigaNotFoundException(String message) {
        super(message);
    }

    public KnjigaNotFoundException(Long id) {
        super("Knjiga sa ID: " + id + " nije pronađena");
    }
}
