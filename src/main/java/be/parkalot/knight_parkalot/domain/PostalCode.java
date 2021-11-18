package be.parkalot.knight_parkalot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "postal_code")
public class PostalCode {
    @Id
    @Column(name = "code")
    private int code;

    @Column(name = "city")
    private String city;

    public PostalCode() {
    }

    public PostalCode(int code, String city) {
        this.code = code;
        this.city = city;
    }

    public int getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostalCode that = (PostalCode) o;
        return code == that.code && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, city);
    }
}
