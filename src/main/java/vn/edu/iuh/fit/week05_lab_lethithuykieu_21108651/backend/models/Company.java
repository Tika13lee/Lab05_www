package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "comp_id", nullable = false)
    private Long id;

    @Column(name = "about", length = 2000)
    private String about;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "comp_name", nullable = false)
    private String compName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "web_url")
    private String webUrl;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", about='" + about + '\'' +
                ", email='" + email + '\'' +
                ", compName='" + compName + '\'' +
                ", phone='" + phone + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", address=" + address +
                '}';
    }
}