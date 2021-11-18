package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @SequenceGenerator(name = "address_seq", sequenceName = "ADDRESS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    private int id;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "house_number")
    private String houseNumber;

    @ManyToOne
    @JoinColumn(name = "postal_code", referencedColumnName = "code")
    private PostalCode postalCode;

    public Address() {
    }

    public Address(String streetName, String houseNumber, PostalCode postalCode) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && Objects.equals(streetName, address.streetName) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetName, houseNumber, postalCode);
    }
}
