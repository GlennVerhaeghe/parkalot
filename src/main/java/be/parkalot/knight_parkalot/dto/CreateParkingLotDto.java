package be.parkalot.knight_parkalot.dto;

public class CreateParkingLotDto {
    private String name;
    private int maxCapacity;
    private CreateContactPersonDto contactPersonDto;
    private AddressDto addressDto;
    private double pricePerHour;
    private int parkingLotCategoryId;
    private int divisionId;

    public CreateParkingLotDto(){

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
