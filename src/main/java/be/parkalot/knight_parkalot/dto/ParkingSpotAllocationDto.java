package be.parkalot.knight_parkalot.dto;

public class ParkingSpotAllocationDto {

    private final int parkingSpotAllocationId;
    private final int parkingLotId;
    private final int memberId;
    private final String licensePlateNumber;

    public ParkingSpotAllocationDto(int parkingSpotAllocationId, int parkingLotId, int memberId, String licensePlateNumber) {
        this.parkingSpotAllocationId = parkingSpotAllocationId;
        this.parkingLotId = parkingLotId;
        this.memberId = memberId;
        this.licensePlateNumber = licensePlateNumber;
    }

    public int getParkingSpotAllocationId() {
        return parkingSpotAllocationId;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }
}
