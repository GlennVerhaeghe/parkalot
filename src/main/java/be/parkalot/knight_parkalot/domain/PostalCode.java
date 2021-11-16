package be.parkalot.knight_parkalot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "postalcode")
public class PostalCode {
    @Id
    @Column(name = "code")
    private int code;

    @Column(name = "city")
    private String city;

    public PostalCode() {
    }

    public int getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }
}
