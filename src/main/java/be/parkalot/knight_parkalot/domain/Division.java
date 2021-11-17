package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;

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
}
