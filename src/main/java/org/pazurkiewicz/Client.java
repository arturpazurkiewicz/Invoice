package org.pazurkiewicz;

public class Client {
    int id;
    String name;
    String NIP;
    String city;
    String streetAndNumber;
    String postcode;

    public Client(int id, String name, String NIP, String city, String streetAndNumber, String postcode) {
        this.id = id;
        this.name = name;
        this.NIP = NIP;
        this.city = city;
        this.streetAndNumber = streetAndNumber;
        this.postcode = postcode;
    }
}
