package be.parkalot.knight_parkalot.service.invoice;

import be.parkalot.knight_parkalot.domain.InvoiceItem;
import be.parkalot.knight_parkalot.domain.Member;
import be.parkalot.knight_parkalot.domain.ParkingSpotAllocation;
import be.parkalot.knight_parkalot.domain.ParkingSpotAllocationStatus;
import be.parkalot.knight_parkalot.service.ParkingSpotAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InvoiceItemService {

    private final ParkingSpotAllocationService parkingSpotAllocationService;

    @Autowired
    public InvoiceItemService(ParkingSpotAllocationService parkingSpotAllocationService) {
        this.parkingSpotAllocationService = parkingSpotAllocationService;
    }

    public List<InvoiceItem> getAllInvoiceItemsByMember(Member member) {
        List<ParkingSpotAllocation> parkingSpotAllocations = parkingSpotAllocationService.getAllInactiveParkingAllocationsByMember(member);
        return generateInvoiceItems(parkingSpotAllocations);
    }

    private List<InvoiceItem> generateInvoiceItems(List<ParkingSpotAllocation> parkingSpotAllocations) {
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        for (ParkingSpotAllocation parkingSpotAllocation : parkingSpotAllocations) {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setParkingSpotAllocation(parkingSpotAllocation);
            invoiceItem.setPrice(calculatePrice(parkingSpotAllocation));
            parkingSpotAllocation.setStatus(ParkingSpotAllocationStatus.INVOICED);
            invoiceItems.add(invoiceItem);
        }
        return invoiceItems;
    }

    private double calculatePrice(ParkingSpotAllocation parkingSpotAllocation) {
        double hours = Math.ceil(Duration.between(parkingSpotAllocation.getStartingTime(), parkingSpotAllocation.getEndingTime()).toMillis()/ 1000d / 60 /60);
        double pricePerHour = parkingSpotAllocation.getParkingLot().getPricePerHour();
        double reduction = parkingSpotAllocation.getMember().getMembershipLevel().getReductionPercentage();
        double fine = calculateFine(hours, parkingSpotAllocation.getMember().getMembershipLevel().getMaxAllowedAllocationHours());

        return ((hours * pricePerHour) * (1 - reduction) + fine);
    }

    private double calculateFine(double hours, int allowedAllocationHours) {
        if (allowedAllocationHours < hours) {
            return (hours - allowedAllocationHours) * 2.5;
        }
        return 0;
    }
}
