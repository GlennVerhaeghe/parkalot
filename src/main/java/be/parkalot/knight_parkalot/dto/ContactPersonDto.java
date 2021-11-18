package be.parkalot.knight_parkalot.dto;

import java.util.Objects;

public class ContactPersonDto {
    private int id;
    private NameDto name;
    private String mobilePhoneNumber;
    private String telephoneNumber;
    private String email;
    private AddressDto addressDto;

    public ContactPersonDto() {
    }

    private ContactPersonDto(Builder builder) {
        id = builder.id;
        name = builder.name;
        mobilePhoneNumber = builder.mobilePhoneNumber;
        telephoneNumber = builder.telephoneNumber;
        email = builder.email;
        addressDto = builder.addressDto;
    }

    public int getId() {
        return id;
    }

    public NameDto getNameDto() {
        return name;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactPersonDto that = (ContactPersonDto) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(mobilePhoneNumber, that.mobilePhoneNumber) && Objects.equals(telephoneNumber, that.telephoneNumber) && Objects.equals(email, that.email) && Objects.equals(addressDto, that.addressDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mobilePhoneNumber, telephoneNumber, email, addressDto);
    }

    public static class Builder {
        private int id;
        private NameDto name;
        private String mobilePhoneNumber;
        private String telephoneNumber;
        private String email;
        private AddressDto addressDto;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(NameDto name) {
            this.name = name;
            return this;
        }

        public Builder withMobilePhoneNumber(String mobilePhoneNumber) {
            this.mobilePhoneNumber = mobilePhoneNumber;
            return this;
        }

        public Builder withTelephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withAddressDto(AddressDto addressDto) {
            this.addressDto = addressDto;
            return this;
        }

        public ContactPersonDto build() {
            return new ContactPersonDto(this);
        }
    }
}
