package be.parkalot.knight_parkalot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parking_lot_category")
public class ParkingLotCategory {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    public ParkingLotCategory() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
