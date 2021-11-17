package be.parkalot.knight_parkalot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
