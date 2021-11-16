package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "parking_spot_allocation")
public class ParkingSpotAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name ="member_id",referencedColumnName = "id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "license_plate_number" , referencedColumnName = "number")
    private LicensePlate licensePlate;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id",referencedColumnName = "id")
    private ParkingLot parkingLot;

    @Column(name = "starting_time")
    private LocalDateTime startingTime;

    @Column(name = "ending_time")
    private LocalDateTime endingTime;

    @Column(name = "stop_now")
    private boolean stopNow;

    public ParkingSpotAllocation() {
    }

    public int getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }

    public boolean isStopNow() {
        return stopNow;
    }
}
