package be.parkalot.knight_parkalot.service.invoice;

import be.parkalot.knight_parkalot.domain.Invoice;
import be.parkalot.knight_parkalot.domain.InvoiceItem;
import be.parkalot.knight_parkalot.domain.Member;
import be.parkalot.knight_parkalot.dto.invoice.InvoiceDto;
import be.parkalot.knight_parkalot.exceptions.InvoiceException;
import be.parkalot.knight_parkalot.exceptions.ParkingLotException;
import be.parkalot.knight_parkalot.mapper.invoice.InvoiceMapper;
import be.parkalot.knight_parkalot.repository.InvoiceRepository;
import be.parkalot.knight_parkalot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemService invoiceItemService;
    private final InvoiceMapper invoiceMapper;
    private final MemberService memberService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceItemService invoiceItemService, InvoiceMapper invoiceMapper, MemberService memberService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceItemService = invoiceItemService;
        this.invoiceMapper = invoiceMapper;
        this.memberService = memberService;
    }

    public InvoiceDto getInvoiceForMemberId(int memberId) {
        Member member = memberService.getMemberById(memberId);
        List<InvoiceItem> invoiceItems = invoiceItemService.getAllInvoiceItemsByMember(member);
        Invoice invoice = new Invoice();
        invoiceItems.forEach(invoice::addInvoiceItem);
        invoice.setClosed(false);
        invoice.setCreationDate(LocalDate.now());
        invoice.setExpirationDate(LocalDate.now().plusDays(30));
        invoice.setTotalPrice(calculateTotalPrice(invoice));
        return invoiceMapper.toDto(invoiceRepository.save(invoice));
    }

    private double calculateTotalPrice(Invoice invoice) {
        return invoice.getInvoiceItems().stream()
                .map(InvoiceItem::getPrice)
                .reduce(0.0, Double::sum);
    }

    public List<InvoiceDto> getAllInvoices() {
        return invoiceRepository.findAll().stream().map(invoiceMapper::toDto).collect(Collectors.toList());
    }

    public InvoiceDto closeInvoice(int invoiceId) {

        assertInvoiceExists(invoiceId);
        Invoice invoice = invoiceRepository.getById(invoiceId);

        if (invoice.isClosed()) {
            throw new InvoiceException("This invoice is already closed");
        }
        invoice.setClosed(true);
        invoice.setDateOfPayment(LocalDate.now());
        return invoiceMapper.toDto(invoice);
    }

    private void assertInvoiceExists(int invoiceId) {
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new InvoiceException("No invoice found with id: " + invoiceId);
        }
    }
}
