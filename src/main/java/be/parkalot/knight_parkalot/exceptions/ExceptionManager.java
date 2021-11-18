package be.parkalot.knight_parkalot.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionManager {

    private final Logger logger = LoggerFactory.getLogger(ExceptionManager.class);

    @ExceptionHandler(MissingArgumentsException.class)
    protected void missingMemberArgumentsException(MissingArgumentsException exception, HttpServletResponse response) throws Exception {
        logger.error(exception.getMessage());
        response.sendError(exception.getStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(NotUniqueException.class)
    protected void notUniqueException(NotUniqueException exception, HttpServletResponse response) throws Exception {
        logger.error(exception.getMessage());
        response.sendError(exception.getStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(DatabaseProblemException.class)
    protected void databaseProblemException(DatabaseProblemException exception, HttpServletResponse response) throws Exception {
        logger.error(exception.getMessage());
        response.sendError(exception.getStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected void handleIllegalArgumentException(IllegalArgumentException exception, HttpServletResponse response) throws Exception {
        logger.error(exception.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(DivisionNotFoundException.class)
    protected void divisionNotFoundException(DivisionNotFoundException exception, HttpServletResponse response) throws Exception {
        logger.error(exception.getMessage());
        response.sendError(exception.getStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(MemberNotFoundException.class)
    protected void memberNotFoundException(MemberNotFoundException exception, HttpServletResponse response) throws Exception {
        logger.error(exception.getMessage());
        response.sendError(exception.getStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(ParkingLotCategoryNotFoundException.class)
    protected void handleParkingLotCategoryNotFound(ParkingLotCategoryNotFoundException exception, HttpServletResponse response) throws Exception {
        logger.error(exception.getMessage());
        response.sendError(exception.getStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(ParkingLotException.class)
    protected void parkingLotNotFoundException(ParkingLotException exception, HttpServletResponse response) throws Exception {
        logger.error(exception.getMessage());
        response.sendError(exception.getStatus().value(), exception.getMessage());
    }

}
