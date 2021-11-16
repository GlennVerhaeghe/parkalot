package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;

@Entity
@Table(name = "parking_lot_category")
public class ParkingLotCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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
