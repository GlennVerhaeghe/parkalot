package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "parking_spot_allocation")
public class ParkingSpotAllocation {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "allocation_seq", sequenceName = "ALLOCATION_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allocation_seq")
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

    @Column(name = "starting_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime startingTime;

    @Column(name = "ending_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime endingTime;

    @Column(name = "inactive")
    private boolean inactive;

    public ParkingSpotAllocation() {
    }

    public ParkingSpotAllocation(Member member, LicensePlate licensePlate, ParkingLot parkingLot) {
        this.member = member;
        this.licensePlate = licensePlate;
        this.parkingLot = parkingLot;
        this.startingTime = LocalDateTime.now();
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

    public boolean isInactive() {
        return inactive;
    }
}
