package app;

import models.*;
import payment.*;
import utils.AuthManager;
import utils.CouponManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer activeUser = null;

        while (activeUser == null) {
            System.out.println("\n=========================================");
            System.out.println("      YEMEK SİPARİŞ SİSTEMİNE HOŞGELDİNİZ");
            System.out.println("=========================================");
            System.out.println("1. Giriş Yap");
            System.out.println("2. Kayıt Ol");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice;
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1;
            }

            if (choice == 0) {
                System.out.println("Çıkış yapılıyor... İyi günler!");
                return;
            } 
            else if (choice == 1) {
                System.out.print("Kullanıcı Adı: ");
                String uName = scanner.nextLine();
                System.out.print("Şifre: ");
                String uPass = scanner.nextLine();

                activeUser = AuthManager.login(uName, uPass);

                if (activeUser != null) {
                    System.out.println("\n>>> Giriş Başarılı! Hoşgeldiniz, " + activeUser.getName() + " " + activeUser.getSurname());
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
                
                System.out.println("Adres (Lütfen tek satırda giriniz): ");
                String address = scanner.nextLine();
                address = address.replace(",", " -"); 

                if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || 
                    password.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                    System.out.println("HATA: Tüm alanları doldurmanız zorunludur!");
                } else {
                    Customer newCustomer = new Customer(name, surname, username, password, address, phone);
                    boolean result = AuthManager.register(newCustomer);
                    if (result) {
                        System.out.println(">>> Kayıt başarılı! Lütfen giriş yapınız.");
                    }
                }
            } 
            else {
                System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
        }
        Restaurant restaurant = new Restaurant("Lezzet Durağı");
        restaurant.addMenuItem(new MenuItem("Lahmacun", 50, "Ana Yemek"));
        restaurant.addMenuItem(new MenuItem("Adana Kebap", 120, "Ana Yemek"));
        restaurant.addMenuItem(new MenuItem("Beyti Sarma", 140, "Ana Yemek"));
        restaurant.addMenuItem(new MenuItem("Mercimek Çorbası", 40, "Çorba"));
        restaurant.addMenuItem(new MenuItem("Ayran", 15, "İçecek"));
        restaurant.addMenuItem(new MenuItem("Şalgam", 20, "İçecek"));
        restaurant.addMenuItem(new MenuItem("Künefe", 80, "Tatlı"));
        restaurant.addMenuItem(new MenuItem("Sütlaç", 60, "Tatlı"));
        Order currentOrder = new Order(activeUser);
        while (true) {
            System.out.println("\n-----------------------------------------");
            System.out.println("            ANA İŞLEM MENÜSÜ");
            System.out.println("-----------------------------------------");
            System.out.println("1. Menüyü Gör");
            System.out.println("2. Sepete Ürün Ekle");
            System.out.println("3. Sipariş Özeti");
            System.out.println("4. Ödeme Yap ve Siparişi Tamamla");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            
            int appChoice;
            try {
                String input = scanner.nextLine();
                appChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                appChoice = -1;
            }

            if (appChoice == 0) {
                System.out.println("Çıkış yapılıyor... Bizi tercih ettiğiniz için teşekkürler!");
                break;
            }

            switch (appChoice) {
                case 1:
                    restaurant.showMenu();
                    break;

                case 2:
                    restaurant.showMenu();
                    System.out.print("\nEklemek istediğiniz ürün numarası: ");
                    try {
                        String input = scanner.nextLine();
                        int index = Integer.parseInt(input) - 1;
                        MenuItem item = restaurant.getItem(index);
                        
                        if (item != null) {
                            currentOrder.addItem(item);
                        } else {
                            System.out.println("HATA: Geçersiz ürün numarası.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("HATA: Lütfen sayı giriniz.");
                    }
                    break;

                case 3:
                    currentOrder.showOrderSummary();
                    break;

                case 4:
                    if (currentOrder.getTotalAmount() <= 0) {
                        System.out.println("Sepetiniz boş! Önce ürün eklemelisiniz.");
                        break;
                    }

                    System.out.println("\n--- ÖDEME EKRANI ---");
                    System.out.print("İndirim kuponunuz var mı? (E/H): ");
                    String hasCoupon = scanner.nextLine();
                    
                    if (hasCoupon.equalsIgnoreCase("E")) {
                        System.out.print("Kupon Kodunu Giriniz: ");
                        String code = scanner.nextLine();
                        Coupon coupon = CouponManager.getCoupon(code);
                        
                        if (coupon != null) {
                            boolean applied = currentOrder.applyCoupon(coupon);
                            if (applied) {
                                System.out.println(">>> TEBRİKLER! %" + coupon.getDiscountPercentage() + " indirim uygulandı.");
                            }
                        } else {
                            System.out.println("UYARI: Geçersiz veya süresi dolmuş kupon kodu.");
                        }
                    }
                    currentOrder.showOrderSummary();
                    System.out.println("\nLütfen Ödeme Yöntemi Seçiniz:");
                    System.out.println("1 - Kredi Kartı");
                    System.out.println("2 - Nakit (Kapıda Ödeme)");
                    System.out.print("Seçiminiz: ");
                    String payStr = scanner.nextLine();
                    
                    boolean paymentSuccess = false;

                    if(payStr.equals("1")) {
                        currentOrder.completeOrder(new CreditCardPayment());
                        paymentSuccess = true;
                    } else if (payStr.equals("2")) {
                        currentOrder.completeOrder(new CashPayment());
                        paymentSuccess = true;
                    } else {
                        System.out.println("HATA: Geçersiz ödeme yöntemi seçildi. İşlem iptal edildi.");
                    }
                    if (paymentSuccess) {
                        System.out.println("\n-----------------------------------------");
                        System.out.println("Hizmetimizden memnun kaldınız mı?");
                        System.out.println("Restoranımızı 1 ile 5 arasında puanlayabilirsiniz.");
                        System.out.print("Puanınız (Atlamak için Enter'a basınız): ");
                        
                        try {
                            String scoreInput = scanner.nextLine();
                            if (!scoreInput.isEmpty()) {
                                int score = Integer.parseInt(scoreInput);
                                restaurant.addRating(score);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Puanlama geçildi.");
                        }
                        System.out.println("\nYeni sipariş için yönlendiriliyorsunuz...");
                        currentOrder = new Order(activeUser);
                    }
                    break;

                default:
                    System.out.println("Geçersiz işlem numarası.");
            }
        }
        scanner.close();
    }
}