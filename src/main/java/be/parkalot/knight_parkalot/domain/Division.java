package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "division")
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "division_Name")
    private String divisionName;

    @Column(name = "original_name")
    private String originalName;

    @Embedded
    private Name directorName;

    @ManyToOne
    @JoinColumn(name = "parent_id",referencedColumnName = "id")
    private Division parentDivision;

    public static Division createSubDivision(String divisionName, String originalName, Name directorName, Division parentDivision) {
        return new Division(divisionName, originalName, directorName, parentDivision);
    }
    public static Division createDivision(String divisionName, String originalName, Name directorName) {
        return new Division(divisionName, originalName, directorName, null);
    }

    public Division() {
    }

    private Division(String divisionName, String originalName, Name directorName, Division parentDivision) {
        this.divisionName = divisionName;
        this.originalName = originalName;
        this.directorName = directorName;
        this.parentDivision = parentDivision;
    }

    public int getId() {
        return id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public Name getDirectorName() {
        return directorName;
    }

    public Division getParentDivision() {
        return parentDivision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return id == division.id && Objects.equals(divisionName, division.divisionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, divisionName);
    }
}
