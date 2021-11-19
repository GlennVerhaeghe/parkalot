package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.LicensePlate;
import be.parkalot.knight_parkalot.domain.Member;
import be.parkalot.knight_parkalot.domain.ParkingLot;
import be.parkalot.knight_parkalot.domain.ParkingSpotAllocation;
import be.parkalot.knight_parkalot.dto.ParkingSpotAllocationDto;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ParkingSpotAllocationMapper {

    public ParkingSpotAllocation toEntity(Member member, LicensePlate licensePlate, ParkingLot parkingLot) {
        return new ParkingSpotAllocation(member, licensePlate, parkingLot);
    }

    public ParkingSpotAllocationDto toDto(ParkingSpotAllocation parkingSpotAllocation) {

        LocalDateTime startTime = parkingSpotAllocation.getStartingTime();
        LocalDateTime stopTime = parkingSpotAllocation.getEndingTime() != null ? parkingSpotAllocation.getEndingTime() : LocalDateTime.now();

        long parkingDurationMs = Duration.between(startTime, stopTime).toMillis();

        long amountOfSeconds = parkingDurationMs / 1000L;
        String parkingDurationFormatted = String.format(
                "%02d:%02d:%02d", new Object[]{Long.valueOf(amountOfSeconds / 3600L), Long.valueOf((amountOfSeconds % 3600L) / 60L), Long.valueOf(amountOfSeconds % 60L)});

        return new ParkingSpotAllocationDto(
                parkingSpotAllocation.getId(),
                parkingSpotAllocation.getParkingLot().getId(),
                parkingSpotAllocation.getMember().getId(),
                parkingSpotAllocation.getLicensePlate().getNumber(), parkingDurationFormatted);
    }


}
