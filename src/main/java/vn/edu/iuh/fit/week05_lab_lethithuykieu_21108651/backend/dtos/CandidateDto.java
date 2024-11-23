package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Address;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Skill;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto implements Serializable {
    Long id;
    String name;
    String email;
    String phone;
    String dob;
    List<Skill> Skills;
    String skillString;
    Address address;
}
