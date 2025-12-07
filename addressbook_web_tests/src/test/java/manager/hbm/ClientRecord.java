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

    public String middlename = new String();
    public String nickname = new String();
    public String company = new String();
    private String title = new String();
    private String home = new String();
    public String mobile = new String();
    public String work = new String();
    public String fax = new String();
    public String email2 = new String();
    public String email3 = new String();
    public String homepage = new String();

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
