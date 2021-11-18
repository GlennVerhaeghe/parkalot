package be.parkalot.knight_parkalot.repository;

import be.parkalot.knight_parkalot.domain.ParkingLotCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotCategoryRepository extends JpaRepository<ParkingLotCategory, Integer> {
}
