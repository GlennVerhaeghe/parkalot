package be.parkalot.knight_parkalot.dto;

public class MembershipLevelDto {

    private String name;
    private double monthlyCost;
    private double reductionPercentage;
    private int maxAllowedAllocationHours;

    public MembershipLevelDto(String name, double monthlyCost, double reductionPercentage, int maxAllowedAllocationHours) {
        this.name = name;
        this.monthlyCost = monthlyCost;
        this.reductionPercentage = reductionPercentage;
        this.maxAllowedAllocationHours = maxAllowedAllocationHours;
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
