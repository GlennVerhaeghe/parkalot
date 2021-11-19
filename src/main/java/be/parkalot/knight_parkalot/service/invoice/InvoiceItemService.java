package be.parkalot.knight_parkalot.service.invoice;

import be.parkalot.knight_parkalot.domain.InvoiceItem;
import be.parkalot.knight_parkalot.domain.Member;
import be.parkalot.knight_parkalot.domain.ParkingSpotAllocation;
import be.parkalot.knight_parkalot.mapper.invoice.InvoiceItemMapper;
import be.parkalot.knight_parkalot.repository.InvoiceItemRepository;
import be.parkalot.knight_parkalot.service.ParkingSpotAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceItemService {

    private final InvoiceItemRepository repository;
    private final InvoiceItemMapper mapper;
    private final ParkingSpotAllocationService parkingSpotAllocationService;

    @Autowired
    public InvoiceItemService(InvoiceItemRepository repository, InvoiceItemMapper mapper, ParkingSpotAllocationService parkingSpotAllocationService) {
        this.repository = repository;
        this.mapper = mapper;
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
            invoiceItems.add(repository.save(invoiceItem));
        }
        return invoiceItems;
    }

    private double calculatePrice(ParkingSpotAllocation parkingSpotAllocation) {
        int hours = (int) Math.ceil(Duration.between(parkingSpotAllocation.getEndingTime(), parkingSpotAllocation.getStartingTime()).toMillis()/ 1000f / 60 /60);
        double pricePerHour = parkingSpotAllocation.getParkingLot().getPricePerHour();
        double reduction = parkingSpotAllocation.getMember().getMembershipLevel().getReductionPercentage();
        double fine = calculateFine(hours, parkingSpotAllocation.getMember().getMembershipLevel().getMaxAllowedAllocationHours());

        return ((hours * pricePerHour) * (1 - reduction) + fine);
    }

    private double calculateFine(int hours, int allowedAllocationHours) {
        if (allowedAllocationHours < hours) {
            return (hours - allowedAllocationHours) * 2.5;
        }
        return 0;
    }


}
