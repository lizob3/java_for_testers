package manager.hbm;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "firstname")
    public String firstName;

    @Column(name = "lastname")
    public String lastName;

    @Column(name = "address")
    public String address;

    @Column(name = "email")
    public String email;

    @Column(name = "email2")
    public String email2;

    @Column(name = "email3")
    public String email3;

    @Column(name = "mobile")
    public String mobilePhone;

    @Column(name = "home")
    public String homePhone;

    @Column(name = "work")
    public String workPhone;

    public String middlename = "middlename";
    public String nickname = "nickname";
    public String company = "company";
    public String title = "title";
    public String fax = "fax";
    public String homepage = "homepage";

    public ContactRecord() {

    }

    public ContactRecord(int id, String firstName, String lastName, String address, String email, String email2,
                         String email3, String mobilePhone, String homePhone, String workPhone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.mobilePhone = mobilePhone;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
    }
}
