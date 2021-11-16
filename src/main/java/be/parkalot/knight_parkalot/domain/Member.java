package be.parkalot.knight_parkalot.domain;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "member")
public class Member {
    @Id
    private int id;

    @Embedded
    private Name name;

    @ManyToOne
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

    @Column(name ="telephone_number")
    private String telephoneNumber;


    @Column(name = "email")
    private String email ;

    @OneToOne
    @JoinColumn(name = "license_plate_number",referencedColumnName = "number")
    private LicensePlate licensePlate;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "membership_level_id",referencedColumnName = "id")
    private MembershipLevel membershipLevel;

    public Member() {
    }

    public int getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }
}
