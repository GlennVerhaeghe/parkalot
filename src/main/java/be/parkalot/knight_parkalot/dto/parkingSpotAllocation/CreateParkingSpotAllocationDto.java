package be.parkalot.knight_parkalot.dto.parkingSpotAllocation;

import be.parkalot.knight_parkalot.dto.member.LicensePlateDto;

import java.util.Objects;

public class CreateParkingSpotAllocationDto {

    private final int memberId;
    private final LicensePlateDto licensePlateDto;
    private final int parkingLotId;

    public CreateParkingSpotAllocationDto(int memberId, LicensePlateDto licensePlateDto, int parkingLotId) {
        this.memberId = memberId;
        this.licensePlateDto = licensePlateDto;
        this.parkingLotId = parkingLotId;
    }

    public int getMemberId() {
        return memberId;
    }

    public LicensePlateDto getLicensePlateDto() {
        return licensePlateDto;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateParkingSpotAllocationDto that = (CreateParkingSpotAllocationDto) o;
        return memberId == that.memberId && parkingLotId == that.parkingLotId && Objects.equals(licensePlateDto, that.licensePlateDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, licensePlateDto, parkingLotId);
    }
}
