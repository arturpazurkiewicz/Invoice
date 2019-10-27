package org.pazurkiewicz;

public class Client {
    public int id;
    public String name;
    public String NIP;
    public String city;
    public String streetAndNumber;
    public String postcode;

    public Client(int id, String name, String NIP, String city, String streetAndNumber, String postcode) {
        this.id = id;
        this.name = name;
        this.NIP = NIP;
        this.city = city;
        this.streetAndNumber = streetAndNumber;
        this.postcode = postcode;
    }
}
