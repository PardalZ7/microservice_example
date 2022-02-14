package br.com.pz7systems.backendProject.BackEnd.Project.services.exceptions;

public class ProcessNotAllowedException extends RuntimeException {

    public ProcessNotAllowedException(String message) {
        super(message);
    }

}
