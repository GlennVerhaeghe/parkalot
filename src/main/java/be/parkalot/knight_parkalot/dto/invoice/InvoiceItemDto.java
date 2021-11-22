package be.parkalot.knight_parkalot.dto.invoice;

import be.parkalot.knight_parkalot.dto.parkingSpotAllocation.ParkingSpotAllocationDto;

public class InvoiceItemDto {
    private final int id;
    private final ParkingSpotAllocationDto parkingSpotAllocationDto;
    private final double price;

    public InvoiceItemDto(int id, ParkingSpotAllocationDto parkingSpotAllocationDto, double price) {
        this.id = id;
        this.parkingSpotAllocationDto = parkingSpotAllocationDto;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public ParkingSpotAllocationDto getParkingSpotAllocationDto() {
        return parkingSpotAllocationDto;
    }

    public double getPrice() {
        return price;
    }
}
