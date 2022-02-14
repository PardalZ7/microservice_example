package br.com.pz7systems.backendProject.BackEnd.Project.shared.DTO;

import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BillDTO extends BaseDTO {

    private Double value;
    private String label;
    private Boolean done;
    private Date payDate;
    private Integer referMonth;
    private Integer referYear;
    private Long userId;

}
