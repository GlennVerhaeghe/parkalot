package be.parkalot.knight_parkalot.repository;

import be.parkalot.knight_parkalot.domain.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {
}
