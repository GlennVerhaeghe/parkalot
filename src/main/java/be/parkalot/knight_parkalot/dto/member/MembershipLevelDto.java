package be.parkalot.knight_parkalot.dto.member;

public class MembershipLevelDto {

    private final String name;
    private final double monthlyCost;
    private final double reductionPercentage;
    private final int maxAllowedAllocationHours;

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
