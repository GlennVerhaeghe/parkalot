package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "parking_lot")
public class ParkingLot {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "parking_lot_seq", sequenceName = "PARKING_LOT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parking_lot_seq")
    private int id;

    @Column(name = "name")
    private String Name;

    @Column(name = "max_capacity")
    private int maxCapacity;

    @ManyToOne
    @JoinColumn(name = "contact_id",referencedColumnName = "id")
    private ContactPerson contactPerson;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

    @Column(name = "price_per_hour")
    private double pricePerHour;

    @ManyToOne
    @JoinColumn(name = "parking_lot_category_id", referencedColumnName = "id")
    private ParkingLotCategory parkingLotCategory;

    @ManyToOne
    @JoinColumn(name = "division_id", referencedColumnName = "id")
    private Division division;

    public ParkingLot() {
    }

    public ParkingLot(String name, int maxCapacity, ContactPerson contactPerson, Address address, double pricePerHour, ParkingLotCategory parkingLotCategory, Division division) {
        Name = name;
        this.maxCapacity = maxCapacity;
        this.contactPerson = contactPerson;
        this.address = address;
        this.pricePerHour = pricePerHour;
        this.parkingLotCategory = parkingLotCategory;
        this.division = division;
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

    public ParkingLotCategory getParkingLotCategory() {
        return parkingLotCategory;
    }

    public Division getDivision() {
        return division;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingLot that = (ParkingLot) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
