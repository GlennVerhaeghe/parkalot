package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;

@Entity
@Table(name = "parking_lot")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String Name;

    @Column(name = "max_capacity")
    private int maxCapacity;

    @ManyToOne
    @JoinColumn(name = "contact_id",referencedColumnName = "id")
    private ContactPerson contactPerson;

    @ManyToOne
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

    @Column(name = "price_per_hour")
    private double pricePerHour;



    public ParkingLot() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public Address getAddress() {
        return address;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }
}
