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

    // Ödeme işlemini burada tetikliyoruz (Polimorfizm kullanımı)
    public void completeOrder(PaymentMethod paymentMethod) {
        customer.placeOrder(); // Interface kullanımı
        paymentMethod.pay(totalAmount); // Polimorfizm kullanımı
        System.out.println("Sipariş başarıyla oluşturuldu!\n");
    }
}