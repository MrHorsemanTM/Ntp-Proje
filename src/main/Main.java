import models.*;
import payment.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

     
        Restaurant restaurant = new Restaurant("Lezzet Durağı");
        restaurant.addMenuItem(new MenuItem("Lahmacun", 50, "Ana Yemek"));
        restaurant.addMenuItem(new MenuItem("Adana Kebap", 120, "Ana Yemek"));
        restaurant.addMenuItem(new MenuItem("Ayran", 15, "İçecek"));
        restaurant.addMenuItem(new MenuItem("Künefe", 80, "Tatlı"));

    
        System.out.println("Hoşgeldiniz! Lütfen bilgilerinizi girin.");
        System.out.print("Adınız: ");
        String name = scanner.nextLine();
        Customer currentCustomer = new Customer(name, "email@test.com", "İstanbul/Kadıköy", "05551234567");

        Order currentOrder = new Order(currentCustomer);

        while (true) {
            System.out.println("\nNe yapmak istersiniz?");
            System.out.println("1. Menüyü Gör");
            System.out.println("2. Sepete Ürün Ekle");
            System.out.println("3. Sipariş Özeti");
            System.out.println("4. Siparişi Tamamla");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
        }    
        
    }
    }