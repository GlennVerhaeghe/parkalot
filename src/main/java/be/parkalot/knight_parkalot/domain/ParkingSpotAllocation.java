package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "parking_spot_allocation")
public class ParkingSpotAllocation {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "allocation_seq", sequenceName = "ALLOCATION_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allocation_seq")
    private int id;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "license_plate_number", referencedColumnName = "number")
    private LicensePlate licensePlate;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id", referencedColumnName = "id")
    private ParkingLot parkingLot;

    @Column(name = "starting_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime startingTime;

    @Column(name = "ending_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime endingTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ParkingSpotAllocationStatus status;

    public ParkingSpotAllocation() {
    }

    public ParkingSpotAllocation(Member member, LicensePlate licensePlate, ParkingLot parkingLot) {
        this.member = member;
        this.licensePlate = licensePlate;
        this.parkingLot = parkingLot;
        this.startingTime = LocalDateTime.now();
        this.status = ParkingSpotAllocationStatus.ACTIVE;
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

    public ParkingSpotAllocationStatus getStatus() {
        return status;
    }

    public void setStatus(ParkingSpotAllocationStatus status) {
        this.status = status;
    }

    public void setEndingTime(LocalDateTime endingTime) {
        this.endingTime = endingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpotAllocation that = (ParkingSpotAllocation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
