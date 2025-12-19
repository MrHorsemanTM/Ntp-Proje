package models;

import ınterface.Orderable;


public class Customer extends User implements Orderable {
 
    private String address;
    private String phoneNumber;

    public Customer(String name, String email, String address, String phoneNumber) {
        super(name, email);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

 
    @Override
    public void placeOrder() {
        System.out.println(name + " kullanıcısı (" + email + ") siparişini onayladı.");
        System.out.println("Teslimat Adresi: " + address);
        System.out.println("İletişim: " + phoneNumber);
    }
    

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}