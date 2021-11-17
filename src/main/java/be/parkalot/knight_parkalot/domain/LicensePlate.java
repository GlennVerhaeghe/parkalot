package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;

@Entity
@Table(name="license_plate")
public class LicensePlate {

    @Id
    @Column(name = "number")
    private String number;

    @Column(name = "country_code")
    private String countryCode;

    public LicensePlate() {
    }

    public LicensePlate(String number, String countryCode) {
        this.number = number;
        this.countryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
