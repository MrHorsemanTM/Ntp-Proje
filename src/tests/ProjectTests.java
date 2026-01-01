package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import models.*;
import utils.AuthManager;
import payment.CreditCardPayment;

public class ProjectTests {

    @Test
    public void testMenuAddition() {
        Restaurant restaurant = new Restaurant("Test Restoranı");
        MenuItem item = new MenuItem("Test Yemek", 100.0, "Ana Yemek");
        
        restaurant.addMenuItem(item);
        
        assertNotNull(restaurant.getItem(0)); 
        assertEquals("Test Yemek", restaurant.getItem(0).getName());
        assertEquals(100.0, restaurant.getItem(0).getPrice(), 0.01);
    }

    @Test
    public void testOrderTotalCalculation() {
        Customer testCustomer = new Customer("Ali", "Veli", "testuser", "123", "Adres", "555");
        Order order = new Order(testCustomer);
        
        order.addItem(new MenuItem("Yemek 1", 50.0, "Yemek"));
        order.addItem(new MenuItem("Yemek 2", 25.0, "Yemek"));
        
        assertEquals(75.0, order.getTotalAmount(), 0.01);
    }


    @Test
    public void testUserRegisterAndLogin() {
        String randomUser = "user" + System.currentTimeMillis();
        
        Customer newCustomer = new Customer("Test", "User", randomUser, "pass123", "Istanbul", "555");
        
        boolean registerResult = AuthManager.register(newCustomer);
        assertTrue("Kayıt başarısız oldu", registerResult);
        
        Customer loggedInUser = AuthManager.login(randomUser, "pass123");
        assertNotNull("Giriş yapılamadı, kullanıcı null döndü", loggedInUser);
        
        assertEquals("Test", loggedInUser.getName());
    }
}