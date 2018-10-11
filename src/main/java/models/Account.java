package models;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name="accountType_id")
    private AccountType accountType;


    private float balance;

    private Date createdAt;


    @OneToMany(mappedBy = "source", fetch = FetchType.LAZY)
    private Set<Transaction> transactionsFrom = new LinkedHashSet<Transaction>();

    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY)
    private Set<Transaction> transactionsTo = new LinkedHashSet<Transaction>();

    public Account() {}

    public Account(Date date){
        this.createdAt = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
        accountType.addAccount(this);
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public Set<Transaction> getTransactionsFrom() {
        return transactionsFrom;
    }

    public void setTransactionsFrom(Set<Transaction> transactions) {
        this.transactionsFrom = transactions;
    }

    public void addTransactionFrom(Transaction transaction){
        transactionsFrom.add(transaction);
    }

    public Set<Transaction> getTransactionsTo() {
        return transactionsTo;
    }

    public void setTransactionsTo(Set<Transaction> transactions) {
        this.transactionsTo = transactions;
    }

    public void addTransactionTo(Transaction transaction){
        transactionsTo.add(transaction);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountTypeDesc=" + accountType.getDescription()+
                ", balance=" + balance +
                ", createdAt=" + createdAt +
                '}';
    }
}
