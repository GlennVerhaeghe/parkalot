package be.parkalot.knight_parkalot.dto.parkingLot;

import be.parkalot.knight_parkalot.dto.contactPerson.CreateContactPersonDto;
import be.parkalot.knight_parkalot.dto.address.AddressDto;

public class CreateParkingLotDto {
    private final String name;
    private final int maxCapacity;
    private final CreateContactPersonDto contactPersonDto;
    private final AddressDto addressDto;
    private final double pricePerHour;
    private final int parkingLotCategoryId;
    private final int divisionId;

    public CreateParkingLotDto(String name, int maxCapacity, CreateContactPersonDto contactPersonDto,
                               AddressDto addressDto, double pricePerHour, int parkingLotCategoryId, int divisionId) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.contactPersonDto = contactPersonDto;
        this.addressDto = addressDto;
        this.pricePerHour = pricePerHour;
        this.parkingLotCategoryId = parkingLotCategoryId;
        this.divisionId = divisionId;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public CreateContactPersonDto getContactPersonDto() {
        return contactPersonDto;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public int getParkingLotCategoryId() {
        return parkingLotCategoryId;
    }

    public int getDivisionId() {
        return divisionId;
    }
}
