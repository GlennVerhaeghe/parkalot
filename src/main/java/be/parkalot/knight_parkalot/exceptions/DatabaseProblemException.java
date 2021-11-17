package be.parkalot.knight_parkalot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DatabaseProblemException extends ResponseStatusException {

    public DatabaseProblemException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
