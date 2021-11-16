package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "streetName")
    private String streetName;

    @Column(name = "houseNumber")
    private String houseNumber;

    @ManyToOne
    @JoinColumn(name = "postal_code",referencedColumnName = "code")
    private PostalCode postalCode;

    public Address() {
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
