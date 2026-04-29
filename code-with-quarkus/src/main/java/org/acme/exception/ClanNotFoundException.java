package org.acme.exception;

public class ClanNotFoundException extends BiblioteckaException {

    public ClanNotFoundException(String message) {
        super(message);
    }

    public ClanNotFoundException(Long id) {
        super("Član sa " + id + " nije pronađen");
    }
}
