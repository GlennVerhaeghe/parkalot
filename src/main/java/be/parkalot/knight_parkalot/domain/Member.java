package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "member_seq", sequenceName = "MEMBER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    private int id;

    @Embedded
    private Name name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "telephone_number")
    private String telephoneNumber;


    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "license_plate_number", referencedColumnName = "number")
    private LicensePlate licensePlate;

    @Column(name = "registration_date", columnDefinition = "DATE")
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "membership_level_id", referencedColumnName = "id")
    private MembershipLevel membershipLevel;

    public Member() {
    }

    public Member(Name name, Address address, String telephoneNumber, String email, LicensePlate licensePlate, MembershipLevel membershipLevel) {
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.licensePlate = licensePlate;
        this.registrationDate = LocalDate.now();
        this.membershipLevel = membershipLevel;
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

    public void setMembershipLevel(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member member = (Member) o;
        return id == member.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
