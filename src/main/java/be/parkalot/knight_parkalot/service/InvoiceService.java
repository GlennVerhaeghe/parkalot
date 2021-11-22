package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.Invoice;
import be.parkalot.knight_parkalot.domain.InvoiceItem;
import be.parkalot.knight_parkalot.domain.Member;
import be.parkalot.knight_parkalot.dto.invoice.InvoiceDto;
import be.parkalot.knight_parkalot.exceptions.InvoiceException;
import be.parkalot.knight_parkalot.mapper.InvoiceMapper;
import be.parkalot.knight_parkalot.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemService invoiceItemService;
    private final InvoiceMapper invoiceMapper;
    private final MemberService memberService;
    private final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceItemService invoiceItemService, InvoiceMapper invoiceMapper, MemberService memberService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceItemService = invoiceItemService;
        this.invoiceMapper = invoiceMapper;
        this.memberService = memberService;
    }

    public InvoiceDto getInvoiceForMemberId(int memberId) {
        logger.info("getInvoiceForMemberID() called");
        Member member = memberService.getMemberById(memberId);
        logger.info("Member created");
        List<InvoiceItem> invoiceItems = invoiceItemService.getAllInvoiceItemsByMember(member);
        logger.info("Got all InvoiceItems By Member");
        Invoice invoice = new Invoice();
        logger.info("Invoice created");

        invoiceItems.forEach(invoice::addInvoiceItem);
        logger.info("InvoiceItems added to Invoice");
        invoice.setClosed(false);
        logger.info("Invoice set to closed = false");
        invoice.setCreationDate(LocalDate.now());
        logger.info("Invoice creationdate set");
        invoice.setExpirationDate(LocalDate.now().plusDays(7));
        logger.info("Invoice expirationDate set");
        invoice.setTotalPrice(calculateTotalPrice(invoice));
        logger.info("Invoice totalPrice set");

        return invoiceMapper.toDto(invoiceRepository.save(invoice));
    }

    public List<InvoiceDto> getAllInvoices() {
        logger.info("getAllInvoices() called");
        return invoiceRepository.findAll().stream().map(invoiceMapper::toDto).collect(Collectors.toList());
    }

    public InvoiceDto closeInvoice(int invoiceId) {
        logger.info("closeInvoice() called");

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

    private double calculateTotalPrice(Invoice invoice) {
        logger.info("calculateTotalPrice() called");
        return invoice.getInvoiceItems().stream()
                .mapToDouble(InvoiceItem::getPrice)
                .sum();
    }
}
