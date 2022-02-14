package br.com.pz7systems.backendProject.BackEnd.Project.view.resources.exceptionHandler;

import br.com.pz7systems.backendProject.BackEnd.Project.services.exceptions.DataIntegrityViolationException;
import br.com.pz7systems.backendProject.BackEnd.Project.services.exceptions.ProcessNotAllowedException;
import br.com.pz7systems.backendProject.BackEnd.Project.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                         HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now()).error(ex.getMessage()).status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> handleResourceNotFoundException(DataIntegrityViolationException ex,
                                                                         HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now()).error(ex.getMessage()).status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @ExceptionHandler(ProcessNotAllowedException.class)
    public ResponseEntity<StandardError> handleProcessNotAllowed(ProcessNotAllowedException ex,
                                                                 HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now()).error(ex.getMessage()).status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
