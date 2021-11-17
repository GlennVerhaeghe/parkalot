package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "house_number")
    private String houseNumber;

    @ManyToOne(cascade = CascadeType.ALL)
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
}
