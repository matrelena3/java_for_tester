package manager.hbm;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "addressbook")
public class ClientRecord {

    @Id
    public int id;

    public String firstname;

    public String lastname;

    public String address;

    public String email;

    public String home;

    public String work;

    public String mobile;

    public String phone2;

    public String middlename = new String();
    public String nickname = new String();
    public String company = new String();
    public String title = new String();
    public String fax = new String();
    public String homepage = new String();

    public String email2;

    public String email3;
    public String address2;



    public ClientRecord() {
    }

    public ClientRecord(int id, String firstname, String lastname, String address, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
    }
}
