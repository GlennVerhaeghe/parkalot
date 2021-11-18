package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.LicensePlate;
import be.parkalot.knight_parkalot.domain.Member;
import be.parkalot.knight_parkalot.domain.ParkingLot;
import be.parkalot.knight_parkalot.domain.ParkingSpotAllocation;
import be.parkalot.knight_parkalot.dto.ParkingSpotAllocationDto;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotAllocationMapper {

    public ParkingSpotAllocation toEntity(Member member, LicensePlate licensePlate, ParkingLot parkingLot) {
        return new ParkingSpotAllocation(member, licensePlate, parkingLot);
    }

    public ParkingSpotAllocationDto toDto(ParkingSpotAllocation parkingSpotAllocation) {
        return new ParkingSpotAllocationDto(
                parkingSpotAllocation.getId(),
                parkingSpotAllocation.getParkingLot().getId(),
                parkingSpotAllocation.getMember().getId(),
                parkingSpotAllocation.getLicensePlate().getNumber());
    }

}
