package be.parkalot.knight_parkalot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="membership_level")
public class MembershipLevel {
    @Id
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
