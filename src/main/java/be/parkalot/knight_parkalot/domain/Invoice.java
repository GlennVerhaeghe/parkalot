package be.parkalot.knight_parkalot.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "invoice_seq", sequenceName = "INVOICE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_seq")
    private int id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "closed")
    private boolean closed;

    @Column(name = "date_of_payment")
    private LocalDate dateOfPayment;

    @Column(name = "total_price")
    private double totalPrice;

    public Invoice() {
    }

    public int getId() {
        return id;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addInvoiceItem(InvoiceItem invoiceItem) {
        this.invoiceItems.add(invoiceItem);
    }
}
