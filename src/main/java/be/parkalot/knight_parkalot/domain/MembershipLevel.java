package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;

@Entity
@Table(name = "membership_level")
public class MembershipLevel {

    public static final int BRONZE_ID = 1;
    public static final int SILVER_ID = 2;
    public static final int GOLD_ID = 3;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "membership_level_seq", sequenceName = "MEMBERSHIP_LEVEL_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "membership_level_seq")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "monthly_cost")
    private double monthlyCost;

    @Column(name = "reduction_percentage")
    private double reductionPercentage;

    @Column(name = "max_allowed_allocation_hours")
    private int maxAllowedAllocationHours;

    public MembershipLevel() {
    }

    public MembershipLevel(String name, double monthlyCost, double reductionPercentage, int maxAllowedAllocationHours) {
        this.name = name;
        this.monthlyCost = monthlyCost;
        this.reductionPercentage = reductionPercentage;
        this.maxAllowedAllocationHours = maxAllowedAllocationHours;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }

    public double getReductionPercentage() {
        return reductionPercentage;
    }

    public int getMaxAllowedAllocationHours() {
        return maxAllowedAllocationHours;
    }
}
