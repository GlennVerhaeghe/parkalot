package be.parkalot.knight_parkalot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvoiceException extends ResponseStatusException {

    public InvoiceException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
