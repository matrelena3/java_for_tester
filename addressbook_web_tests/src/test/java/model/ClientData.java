package model;


public record ClientData(String firstname, String lastname , String address, String home, String email) {

    public ClientData() {
        this("", "", "", "", "");
    }

    public ClientData withName(String firstname, String lastname) {
        return new ClientData(firstname, lastname, this.address, this.home, this.email);
    }
}