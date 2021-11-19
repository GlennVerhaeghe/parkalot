package be.parkalot.knight_parkalot.mapper.invoice;

import be.parkalot.knight_parkalot.domain.InvoiceItem;
import be.parkalot.knight_parkalot.dto.invoice.InvoiceItemDto;
import be.parkalot.knight_parkalot.mapper.ParkingSpotAllocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceItemMapper {

    private final ParkingSpotAllocationMapper parkingSpotAllocationMapper;

    @Autowired
    public InvoiceItemMapper(ParkingSpotAllocationMapper parkingSpotAllocationMapper) {
        this.parkingSpotAllocationMapper = parkingSpotAllocationMapper;
    }

    public InvoiceItemDto toDto(InvoiceItem invoiceItem) {
        return new InvoiceItemDto(invoiceItem.getId(),
                parkingSpotAllocationMapper.toDto(invoiceItem.getParkingSpotAllocation()),
                invoiceItem.getPrice());
    }
}
