package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}