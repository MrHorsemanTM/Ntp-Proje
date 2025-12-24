package payment;

public class CashPayment extends PaymentMethod {
	
    @Override
    
    public void pay(double amount) {
        System.out.println("Kapıda Nakit olarak " + amount + " TL ödenecek.");
    }
}