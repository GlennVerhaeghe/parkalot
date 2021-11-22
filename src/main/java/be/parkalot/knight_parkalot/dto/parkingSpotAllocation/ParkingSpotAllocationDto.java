package be.parkalot.knight_parkalot.dto.parkingSpotAllocation;

public class ParkingSpotAllocationDto {

    private final int parkingSpotAllocationId;
    private final int parkingLotId;
    private final int memberId;
    private final String licensePlateNumber;
    private final String parkingDuration;

    public ParkingSpotAllocationDto(int parkingSpotAllocationId, int parkingLotId, int memberId, String licensePlateNumber, String parkingDuration) {
        this.parkingSpotAllocationId = parkingSpotAllocationId;
        this.parkingLotId = parkingLotId;
        this.memberId = memberId;
        this.licensePlateNumber = licensePlateNumber;
        this.parkingDuration = parkingDuration;
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

    public String getParkingDuration() {
        return parkingDuration;
    }
}
