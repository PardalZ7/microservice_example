package br.com.pz7systems.backendProject.BackEnd.Project.shared.DTO;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseDTO {

    protected Long id;
    protected Date dataChangeLastModifiedTime;
    protected Date dataChangeCreatedTime;
    protected Boolean deleted;

}
