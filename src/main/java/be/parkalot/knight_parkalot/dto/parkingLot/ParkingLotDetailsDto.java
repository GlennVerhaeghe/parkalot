package be.parkalot.knight_parkalot.dto.parkingLot;

import be.parkalot.knight_parkalot.dto.contactPerson.ContactPersonDto;
import be.parkalot.knight_parkalot.dto.division.DivisionDto;
import be.parkalot.knight_parkalot.dto.address.AddressDto;

public class ParkingLotDetailsDto {
    private int id;
    private String name;
    private int maxCapacity;
    private ContactPersonDto contactPersonDto;
    private AddressDto addressDto;
    private double pricePerHour;
    private String categoryName;
    private DivisionDto divisionDto;

    public ParkingLotDetailsDto() {
    }

    public ParkingLotDetailsDto(Builder builder) {
        id = builder.id;
        name = builder.name;
        maxCapacity = builder.maxCapacity;
        contactPersonDto = builder.contactPersonDto;
        addressDto = builder.addressDto;
        pricePerHour = builder.pricePerHour;
        categoryName = builder.categoryName;
        divisionDto = builder.division;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public ContactPersonDto getContactPersonDto() {
        return contactPersonDto;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public String getParkingLotCategoryName() {
        return categoryName;
    }

    public DivisionDto getDivisionDto() {
        return divisionDto;
    }

    public static class Builder {
        private int id;
        private String name;
        private int maxCapacity;
        private ContactPersonDto contactPersonDto;
        private AddressDto addressDto;
        private double pricePerHour;
        private String categoryName;
        private DivisionDto division;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withMaxCapacity(int maxCapacity) {
            this.maxCapacity = maxCapacity;
            return this;
        }

        public Builder withContactPersonDto(ContactPersonDto contactPersonDto) {
            this.contactPersonDto = contactPersonDto;
            return this;
        }

        public Builder withAddressDto(AddressDto addressDto) {
            this.addressDto = addressDto;
            return this;
        }

        public Builder withPricePerHour(double pricePerHour) {
            this.pricePerHour = pricePerHour;
            return this;
        }

        public Builder withParkingLotCategoryName(String parkingLotCategoryName) {
            this.categoryName = parkingLotCategoryName;
            return this;
        }

        public Builder withDivision(DivisionDto divisionDto) {
            this.division = divisionDto;
            return this;
        }

        public ParkingLotDetailsDto build() {
            return new ParkingLotDetailsDto(this);
        }
    }
}
