package models;

import ınterface.Orderable;

public class Customer extends User implements Orderable {
    private String address;
    private String phoneNumber;

    public Customer(String name, String surname, String username, String password, String address, String phoneNumber) {
        super(name, surname, username, password);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void placeOrder() {
        System.out.println("Sayın " + name + " " + surname + ", siparişiniz onaylandı.");
        System.out.println("Teslimat Adresi: " + address);
        System.out.println("İletişim: " + phoneNumber);
    }
    
    public String toFileFormat() {
        return username + "," + password + "," + name + "," + surname + "," + phoneNumber + "," + address;
    }

    public String getAddress() { return address; }
}