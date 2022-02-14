package br.com.pz7systems.backendProject.BackEnd.Project.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException{

    public DataIntegrityViolationException(String message) {
        super(message);
    }

}
