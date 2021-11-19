package be.parkalot.knight_parkalot.repository;

import be.parkalot.knight_parkalot.domain.LicensePlate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicensePlateRepository extends JpaRepository<LicensePlate, String> {
}
