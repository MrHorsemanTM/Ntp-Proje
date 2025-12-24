package payment;

public class CreditCardPayment extends PaymentMethod {
	
    @Override
    
    public void pay(double amount) {
        System.out.println("Kredi Kartı ile " + amount + " TL ödendi. Banka ile iletişim kuruluyor...");
    }
}