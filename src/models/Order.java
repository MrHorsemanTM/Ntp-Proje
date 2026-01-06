package models;

import payment.PaymentMethod;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer;
    private List<MenuItem> items;
    private double totalAmount;
    private Coupon appliedCoupon; 

    public Order(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
        this.appliedCoupon = null; 
    }

    public void addItem(MenuItem item) {
        items.add(item);
        totalAmount += item.getPrice();
        System.out.println(item.getName() + " sepete eklendi.");
    }
 
    public boolean applyCoupon(Coupon coupon) {
        if (this.appliedCoupon != null) {
            System.out.println("Zaten bir kupon tanımlı!");
            return false;
        }
        this.appliedCoupon = coupon;
        return true;
    }

    public double getDiscountedAmount() {
        if (appliedCoupon == null) {
            return totalAmount;
        }
        double discount = (totalAmount * appliedCoupon.getDiscountPercentage()) / 100;
        return totalAmount - discount;
    }

    public void showOrderSummary() {
        System.out.println("\n--- Sipariş Özeti ---");
        if(items.isEmpty()) {
            System.out.println("Sepetiniz boş.");
            return;
        } 
        
        for (MenuItem item : items) {
            System.out.println(item.getName() + ": " + item.getPrice() + " TL");
        }
        
        System.out.println("-------------------------");
        System.out.println("Ara Toplam: " + totalAmount + " TL");

        if (appliedCoupon != null) {
            double discountValue = totalAmount - getDiscountedAmount();
            System.out.println("Kupon İndirimi (" + appliedCoupon.getCode() + "): -" + discountValue + " TL");
            System.out.println("ÖDENECEK TUTAR: " + getDiscountedAmount() + " TL");
        } else {
            System.out.println("ÖDENECEK TUTAR: " + totalAmount + " TL");
        }
    }

    public void completeOrder(PaymentMethod paymentMethod) {
        if(items.isEmpty()) {
            System.out.println("Sepet boş, sipariş tamamlanamaz!");
            return;
        }
        customer.placeOrder();
        paymentMethod.pay(getDiscountedAmount());
        System.out.println("Sipariş başarıyla oluşturuldu!\n");
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}