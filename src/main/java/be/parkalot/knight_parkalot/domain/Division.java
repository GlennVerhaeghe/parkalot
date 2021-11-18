package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "division")
public class Division {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "division_seq", sequenceName = "DIVISION_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "division_seq")
    private int id;

    @Column(name = "division_Name")
    private String divisionName;

    @Column(name = "original_name")
    private String originalName;

    @Embedded
    private Name directorName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id",referencedColumnName = "id")
    private Division parentDivision;

    public Division() {
    }

    public Division(String divisionName, String originalName, Name directorName, Division parentDivision) {
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

    public int getParentId() {
        if (parentDivision == null) {
            return 0;
        }
        return parentDivision.getId();
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
