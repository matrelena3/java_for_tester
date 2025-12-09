package model;


public record ClientData(
        String id,
        String firstname,
        String lastname ,
        String address,
        String home,
        String email,
        String photo,
        String mobile,
        String work,
        String secondary,
        String email2,
        String email3,
        String address2) {

    public ClientData() {this("","", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ClientData withId(String id) {
        return new ClientData(id, this.firstname, this.lastname, this.address, this.home, this.email, this.photo, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }
    public ClientData withFirstname(String firstname) {
        return new ClientData(this.id, firstname, this.lastname, this.address, this.home, this.email, this.photo, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }
    public ClientData withLastname(String lastname) {
        return new ClientData(this.id, this.firstname, lastname, this.address, this.home, this.email, this.photo, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }
    public ClientData withName(String firstname, String lastname) {
        return new ClientData(this.id, firstname, lastname, this.address, this.home, this.email, this.photo, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }
    public ClientData withAddress(String address) {
        return new ClientData(this.id, this.firstname, this.lastname, address, this.home, this.email, this.photo, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }
    public ClientData withHome(String home) {
        return new ClientData(this.id, this.firstname, this.lastname, this.address, home, this.email, this.photo, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }
    public ClientData withEmail(String email) {
        return new ClientData(this.id, this.firstname, this.lastname, this.address, this.home, email, this.photo, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }
    public ClientData withPhoto(String photo) {
            return new ClientData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, photo, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
        }
    public ClientData withWork(String work) {
        return new ClientData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, this.photo, this.mobile, work, this.secondary, this.email2, this.email3, this.address2);
    }
    public ClientData withMobile(String mobile) {
        return new ClientData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, this.photo, mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }
    public ClientData withSecondary(String secondary) {
        return new ClientData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, this.photo, this.mobile, this.work, secondary, this.email2, this.email3, this.address2);
    }

    public ClientData withEmail2(String email2) {
        return new ClientData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, this.photo, this.mobile, this.work, this.secondary, email2, this.email3, this.address2);
    }

    public ClientData withEmail3(String email3) {
        return new ClientData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, this.photo, this.mobile, this.work, this.secondary, this.email2, email3, this.address2);
    }

    public ClientData withAddress2(String address2) {
        return new ClientData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, this.photo, this.mobile, this.work, this.secondary, this.email2, this.email3, address2);
    }

}
