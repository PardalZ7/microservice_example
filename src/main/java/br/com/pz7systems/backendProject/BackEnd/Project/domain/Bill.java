package br.com.pz7systems.backendProject.BackEnd.Project.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_Bills")
@EqualsAndHashCode(callSuper = true)
public class Bill extends BaseEntity {

    private Double value;
    private String label;

    @Column(name = "done", columnDefinition="boolean default false")
    private boolean done = false;

    @Column(name = "payDate", nullable = true)
    private Date payDate;
    private Integer referMonth;
    private Integer referYear;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

}
