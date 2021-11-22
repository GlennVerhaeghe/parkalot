package be.parkalot.knight_parkalot.dto.parkingLot;

public class ParkingLotDto {
    private final int id;
    private final String name;
    private final int maxCapacity;
    private final String contactPersonEmail;
    private final String contactPersonPhoneNumber;

    public ParkingLotDto(Builder builder) {
        id = builder.id;
        name = builder.name;
        maxCapacity = builder.maxCapacity;
        contactPersonEmail = builder.contactPersonEmail;
        contactPersonPhoneNumber = builder.contactPersonPhoneNumber;
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

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public String getContactPersonPhoneNumber() {
        return contactPersonPhoneNumber;
    }

    public static class Builder {
        private int id;
        private String name;
        private int maxCapacity;
        private String contactPersonEmail;
        private String contactPersonPhoneNumber;

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

        public Builder withContactPersonEmail(String contactPersonEmail) {
            this.contactPersonEmail = contactPersonEmail;
            return this;
        }

        public Builder withContactPersonPhoneNumber(String contactPersonPhoneNumber) {
            this.contactPersonPhoneNumber = contactPersonPhoneNumber;
            return this;
        }

        public ParkingLotDto build() {
            return new ParkingLotDto(this);
        }
    }
}
