package be.parkalot.knight_parkalot.repository;

import be.parkalot.knight_parkalot.domain.ParkingSpotAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotAllocationRepository extends JpaRepository<ParkingSpotAllocation, Integer> {
    List<ParkingSpotAllocation> findByOrderByStartingTimeAsc();
    List<ParkingSpotAllocation> findByOrderByStartingTimeDesc();

    List<ParkingSpotAllocation> findAllByLicensePlate();
}
