package br.com.pz7systems.backendProject.BackEnd.Project.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_Users")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Column(unique = true)
    private String email;
    private String pass;
    private String name;

}
