package be.parkalot.knight_parkalot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ParkingLotCategoryNotFoundException extends ResponseStatusException {
    public ParkingLotCategoryNotFoundException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
