package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;

@Entity
@Table(name = "parking_lot_category")
public class ParkingLotCategory {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "parking_lot_category_seq", sequenceName = "PARKING_LOT_CATEGORY_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parking_lot_category_seq")
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
