package ferramong.scheduler.controllers;

import ferramong.scheduler.exceptions.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for handling errors.
 */
@RestControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(generateErrorResponse(ex), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        return new ResponseEntity<>(generateErrorResponse(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIllegalArgumentExceptions(Exception ex,
                                                                        WebRequest request) {
        return new ResponseEntity<>(generateErrorResponse(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<Object> handleBadCredentialsException(UnauthorizedException ex,
                                                                      WebRequest request) {
        return new ResponseEntity<>(generateErrorResponse(ex), HttpStatus.UNAUTHORIZED);
    }

    private Map<String, String> generateErrorResponse(Exception ex) {
        Map<String, String> response = new HashMap<>();

        response.put("status", "fail");
        response.put("datetime", new Date().toString());
        response.put("message", ex.getLocalizedMessage());

        return response;
    }
}
