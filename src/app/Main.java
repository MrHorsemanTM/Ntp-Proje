package app;
import models.*;
import payment.*;
import utils.AuthManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer activeUser = null;

        while (activeUser == null) {
            System.out.println("\n=== YEMEK SİPARİŞ SİSTEMİ ===");
            System.out.println("1. Giriş Yap");
            System.out.println("2. Kayıt Ol");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            if (choice == 0) {
                System.out.println("Çıkış yapılıyor...");
                return;
            } 
            else if (choice == 1) {
       
                System.out.print("Kullanıcı Adı: ");
                String uName = scanner.nextLine();
                System.out.print("Şifre: ");
                String uPass = scanner.nextLine();

                activeUser = AuthManager.login(uName, uPass);

                if (activeUser != null) {
                    System.out.println("\nGiriş Başarılı! Hoşgeldiniz, " + activeUser.getName());
                } else {
                    System.out.println("HATA: Kullanıcı adı veya şifre yanlış!");
                }
            } 
            else if (choice == 2) {
               System.out.println("\n--- YENİ KULLANICI KAYDI ---");
                System.out.print("Adınız: "); String name = scanner.nextLine();
                System.out.print("Soyadınız: "); String surname = scanner.nextLine();
                System.out.print("Kullanıcı Adı: "); String username = scanner.nextLine();
                System.out.print("Şifre: "); String password = scanner.nextLine();
                System.out.print("Telefon: "); String phone = scanner.nextLine();
                
                System.out.println("Adres (Tek satırda giriniz): ");
                String address = scanner.nextLine();
                address = address.replace(",", " -"); 

                if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || 
                    password.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                    System.out.println("HATA: Tüm alanları doldurmalısınız!");
                } else {
                    Customer newCustomer = new Customer(name, surname, username, password, address, phone);
                    boolean result = AuthManager.register(newCustomer);
                    if (result) {
                        System.out.println("Kayıt başarılı! Lütfen giriş yapınız.");
                    }
                }
            } 
            else {
                System.out.println("Geçersiz seçim.");
            }
        }
        Restaurant restaurant = new Restaurant("Lezzet Durağı");
        restaurant.addMenuItem(new MenuItem("Lahmacun", 50, "Ana Yemek"));
        restaurant.addMenuItem(new MenuItem("Adana Kebap", 120, "Ana Yemek"));
        restaurant.addMenuItem(new MenuItem("Ayran", 15, "İçecek"));
        restaurant.addMenuItem(new MenuItem("Künefe", 80, "Tatlı"));

        Order currentOrder = new Order(activeUser);

        while (true) {
            System.out.println("\n--- ANA MENÜ ---");
            System.out.println("1. Menüyü Gör");
            System.out.println("2. Sepete Ürün Ekle");
            System.out.println("3. Sipariş Özeti");
            System.out.println("4. Siparişi Tamamla");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            
            int appChoice;
            try {
                appChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                appChoice = -1;
            }

            if (appChoice == 0) break;

            switch (appChoice) {
                case 1:
                    restaurant.showMenu();
                    break;
                case 2:
                    restaurant.showMenu();
                    System.out.print("Ürün Numarası: ");
                    try {
                        int index = Integer.parseInt(scanner.nextLine()) - 1;
                        MenuItem item = restaurant.getItem(index);
                        if (item != null) currentOrder.addItem(item);
                        else System.out.println("Geçersiz ürün numarası.");
                    } catch (NumberFormatException e) {
                        System.out.println("Lütfen sayı giriniz.");
                    }
                    break;
                case 3:
                    currentOrder.showOrderSummary();
                    break;
                case 4:
                    System.out.println("Ödeme Yöntemi: 1-Kredi Kartı, 2-Nakit");
                    String payStr = scanner.nextLine();
                    if(payStr.equals("1")) {
                        currentOrder.completeOrder(new CreditCardPayment());
                        currentOrder = new Order(activeUser); 
                    } else if (payStr.equals("2")) {
                        currentOrder.completeOrder(new CashPayment());
                        currentOrder = new Order(activeUser);
                    } else {
                        System.out.println("Geçersiz ödeme yöntemi.");
                    }
                    break;
                default:
                    System.out.println("Geçersiz işlem.");
            }
        }
        System.out.println("İyi günler dileriz!");
        scanner.close();
    }
}