package be.parkalot.knight_parkalot.service.invoice;

import be.parkalot.knight_parkalot.domain.InvoiceItem;
import be.parkalot.knight_parkalot.domain.Member;
import be.parkalot.knight_parkalot.domain.ParkingSpotAllocation;
import be.parkalot.knight_parkalot.mapper.invoice.InvoiceItemMapper;
import be.parkalot.knight_parkalot.repository.InvoiceItemRepository;
import be.parkalot.knight_parkalot.service.ParkingSpotAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return null;
    }
}
