package utils;

import models.Customer;
import java.io.*;

public class AuthManager {
    private static final String FILE_PATH = "users.txt";

    public static boolean register(Customer customer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(customer.toFileFormat());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
            return false;
        }
    }

    public static Customer login(String username, String password) {
        File file = new File(FILE_PATH);
        if (!file.exists()) return null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                
                if (data.length < 6) continue;

                String fileUser = data[0];
                String filePass = data[1];

                if (fileUser.equals(username) && filePass.equals(password)) {
                    String name = data[2];
                    String surname = data[3];
                    String phone = data[4];
                    String address = data[5];
                    
                    return new Customer(name, surname, fileUser, filePass, address, phone);
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
        return null;
    }
}