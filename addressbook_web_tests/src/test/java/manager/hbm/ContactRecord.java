package manager.hbm;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    @Column(name = "mobile")
    public String mobilePhone;

    public ContactRecord() {

    }

    public ContactRecord(int id, String firstName, String lastname, String address, String email, String mobilePhone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastname;
        this.address = address;
        this.email = email;
        this.mobilePhone = mobilePhone;
    }
}
