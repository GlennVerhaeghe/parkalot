package be.parkalot.knight_parkalot.domain;

public enum ParkingSpotAllocationStatus {
    ACTIVE(true), INACTIVE(false), INVOICED(false);

    private final boolean isActive;

    ParkingSpotAllocationStatus(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }
}
