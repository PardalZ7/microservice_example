package br.com.pz7systems.backendProject.BackEnd.Project.view.model;

import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BillResponse {

    protected Long id;
    protected Date dataChangeLastModifiedTime;
    protected Date dataChangeCreatedTime;
    protected Boolean deleted;
    private Double value;
    private String label;
    private Boolean done;
    private Date payDate;
    private Integer referMonth;
    private Integer referYear;
    private Long userId;

}
