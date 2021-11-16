package be.parkalot.knight_parkalot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public String getNumber() {
        return number;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
