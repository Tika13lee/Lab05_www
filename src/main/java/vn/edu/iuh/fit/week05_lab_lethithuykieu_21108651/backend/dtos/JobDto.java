package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Company;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Skill;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JobDto implements Serializable {
    Long id;
    String jobName;
    String jobDesc;
    List<Skill> Skills;
    String skillString;
    Company company;

}