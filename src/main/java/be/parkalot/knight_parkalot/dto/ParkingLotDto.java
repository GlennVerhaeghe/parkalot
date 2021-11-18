package be.parkalot.knight_parkalot.dto;

public class ParkingLotDto {
    private int id;
    private String name;
    private int maxCapacity;
    private ContactPersonDto contactPersonDto;
    private AddressDto addressDto;
    private double pricePerHour;
    private int parkingLotCategoryId;
    private int divisionId;

    public ParkingLotDto(Builder builder) {
        id = builder.id;;
        name = builder.name;
        maxCapacity = builder.maxCapacity;
        contactPersonDto = builder.contactPersonDto;
        addressDto = builder.addressDto;
        pricePerHour = builder.pricePerHour;
        parkingLotCategoryId = builder.parkingLotCategoryId;
        divisionId = builder.divisionId;
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

    public int getParkingLotCategoryId() {
        return parkingLotCategoryId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public static class Builder {
        private int id;
        private String name;
        private int maxCapacity;
        private ContactPersonDto contactPersonDto;
        private AddressDto addressDto;
        private double pricePerHour;
        private int parkingLotCategoryId;
        private int divisionId;

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

        public Builder withParkingLotCategoryId(int parkingLotCategoryId) {
            this.parkingLotCategoryId = parkingLotCategoryId;
            return this;
        }

        public Builder withDivisionId(int divisionId) {
            this.divisionId = divisionId;
            return this;
        }

        public ParkingLotDto build() {
            return new ParkingLotDto(this);
        }
    }
}
