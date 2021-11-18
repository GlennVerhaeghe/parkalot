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
