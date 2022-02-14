package br.com.pz7systems.backendProject.BackEnd.Project.view.model;

import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    protected Long id;
    protected Date dataChangeLastModifiedTime;
    protected Date dataChangeCreatedTime;
    protected Boolean deleted;
    private String name;
    private String email;

}
