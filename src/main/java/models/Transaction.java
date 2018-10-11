package models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float amount;

    @ManyToOne
    @JoinColumn(name="sourceAccount_id")
    private Account source;

    @ManyToOne
    @JoinColumn(name="destinationAccount_id")
    private Account destination;

    public Transaction(){}

    private Date date;

    private String description;

    public Transaction(float amount, Account source, Account destination ,String description){
        this.amount = amount;
        this.source = source;
        this.destination = destination;
        this.description = description;
        this.date = new Date();
        source.addTransactionFrom(this);
        destination.addTransactionTo(this);
    }


    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Account getSource() {
        return source;
    }

    public void setSource(Account source) {
        this.source = source;
    }

    public Account getDestination() {
        return destination;
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
