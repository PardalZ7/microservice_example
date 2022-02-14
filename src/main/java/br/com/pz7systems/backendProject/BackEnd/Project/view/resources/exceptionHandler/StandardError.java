package br.com.pz7systems.backendProject.BackEnd.Project.view.resources.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
@AllArgsConstructor
public class StandardError {

    private LocalDateTime timeStamp;
    private Integer status;
    private String error;
    private String path;

}
