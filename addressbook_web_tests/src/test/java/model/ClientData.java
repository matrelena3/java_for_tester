package model;


public record ClientData(String id, String firstname, String lastname , String address, String home, String email) {

    public ClientData() {this("","", "", "", "", "");
    }

    public ClientData withId(String id) {
        return new ClientData(id, this.firstname, this.lastname, this.address, this.home, this.email);
    }
    public ClientData withName(String firstname, String lastname) {
        return new ClientData(this.id, firstname, lastname, this.address, this.home, this.email);
    }
    public ClientData withAddress(String address) {
        return new ClientData(this.id, this.firstname, this.lastname, address, this.home, this.email);
    }
    public ClientData withHome(String home) {
        return new ClientData(this.id, this.firstname, this.lastname, this.address, home, this.email);
    }
    public ClientData withEmail(String email) {
        return new ClientData(this.id, this.firstname, this.lastname, this.address, this.home, email);
    }
}
