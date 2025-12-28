package models;
import payment.PaymentMethod;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer;
    private List<MenuItem> items;
    private double totalAmount;

    public Order(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addItem(MenuItem item) {
        items.add(item);
        totalAmount += item.getPrice();
        System.out.println(item.getName() + " sepete eklendi.");
    }
    public void showOrderSummary() {
        System.out.println("\n--- Sipariş Özeti ---");
        for (MenuItem item : items) {
            System.out.println(item.getName() + ": " + item.getPrice() + " TL");
        }
        System.out.println("Toplam Tutar: " + totalAmount + " TL");
    }

    public void completeOrder(PaymentMethod paymentMethod) {
        customer.placeOrder(); 
        paymentMethod.pay(totalAmount);
        System.out.println("Sipariş başarıyla oluşturuldu!\n");
    }
}