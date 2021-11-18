package be.parkalot.knight_parkalot.domain;

import jdk.jfr.Enabled;

import javax.persistence.*;

@Entity
@Table(name = "contact_person")
public class ContactPerson {

    @Id
    @SequenceGenerator(name = "contact_person_seq", sequenceName = "CONTACT_PERSON_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_person_seq")
    private int id;

    @Embedded
    private Name name;

    @Column(name = "mobile_number")
    private String mobilePhoneNumber;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public ContactPerson() {
    }

    public int getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
}
