package dprodrigues.com.passin.config;

import dprodrigues.com.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import dprodrigues.com.passin.domain.attendee.exceptions.AttendeeNotFoundException;
import dprodrigues.com.passin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import dprodrigues.com.passin.domain.event.exceptions.EventFullException;
import dprodrigues.com.passin.domain.event.exceptions.EventNotFoundException;
import dprodrigues.com.passin.dto.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity handleEventNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EventFullException.class)
    public ResponseEntity<ErrorResponseDTO> handleEventFullException(EventFullException exception) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(exception.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity handleAttendeeNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeeAlreadyExistException.class)
    public ResponseEntity handleAttendeeAlreadyExist() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(CheckInAlreadyExistsException.class)
    public ResponseEntity handleCheckInAlreadyExistsException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
