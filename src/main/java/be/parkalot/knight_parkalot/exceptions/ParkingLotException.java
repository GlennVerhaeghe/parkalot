package be.parkalot.knight_parkalot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ParkingLotException extends ResponseStatusException {

    public ParkingLotException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
