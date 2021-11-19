package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LicensePlate that = (LicensePlate) o;
        return Objects.equals(number, that.number) && Objects.equals(countryCode, that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, countryCode);
    }

    @Override
    public String toString() {
        return "LicensePlate{" +
                "number='" + number + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
