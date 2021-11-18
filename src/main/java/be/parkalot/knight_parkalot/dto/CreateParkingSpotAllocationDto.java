package be.parkalot.knight_parkalot.dto;


import java.util.Objects;

public class CreateParkingSpotAllocationDto {

    private final int memberId;
    private final String licensePlateNumber;
    private final int parkingLotId;

    public CreateParkingSpotAllocationDto(int memberId, String licensePlateNumber, int parkingLotId) {
        this.memberId = memberId;
        this.licensePlateNumber = licensePlateNumber;
        this.parkingLotId = parkingLotId;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateParkingSpotAllocationDto that = (CreateParkingSpotAllocationDto) o;
        return memberId == that.memberId && parkingLotId == that.parkingLotId && Objects.equals(licensePlateNumber, that.licensePlateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, licensePlateNumber, parkingLotId);
    }
}
