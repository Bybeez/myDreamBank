package models;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    private int id;

    private String name;

    private String firstname;

    @Column(unique = true)

    private String mail;

    @Id
    @Column(unique = true)
    private String login;

    private String password;

    private String phone;

    private Date birthDate;

    private String address;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Account> accounts = new LinkedHashSet<Account>();


    public User(String name, String firstname, String mail, String login, String password, String phone, Date birthDate, String address) {
        this.name = name;
        this.firstname = firstname;
        this.mail = mail;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account){
        account.setOwner(this);
        accounts.add(account);
    }

    @Override
    public String toString() {
        return "[ name : " + name + ", firstname : " + firstname + ", mail : "+ mail + ", login : " + login + ", password : " + password + ", phone : " + phone + ", birthdate : " + birthDate.toString() + ", address : " + address + "]";
    }
}

