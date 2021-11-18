package be.parkalot.knight_parkalot.repository;

import be.parkalot.knight_parkalot.domain.ParkingSpotAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotAllocationRepository extends JpaRepository<ParkingSpotAllocation, Integer> {

}
