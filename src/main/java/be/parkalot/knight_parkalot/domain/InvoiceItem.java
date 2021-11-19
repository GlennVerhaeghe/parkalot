package be.parkalot.knight_parkalot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_item")
public class InvoiceItem {

    @Id
    @SequenceGenerator(name = "invoice_item_seq", sequenceName = "INVOICE_ITEM_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_item_seq")
    private int id;

    @OneToOne
    @JoinColumn(name = "parking_spot_allocation_id", referencedColumnName = "id")
    private ParkingSpotAllocation parkingSpotAllocation;

    @Column(name = "price")
    private double price;

    public InvoiceItem() {
    }

    public ParkingSpotAllocation getParkingSpotAllocation() {
        return parkingSpotAllocation;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public InvoiceItem setParkingSpotAllocation(ParkingSpotAllocation parkingSpotAllocation) {
        this.parkingSpotAllocation = parkingSpotAllocation;
        return this;
    }
}
