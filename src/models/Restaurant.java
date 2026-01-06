package models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private List<MenuItem> menu;
    private List<Integer> ratings; 

    public Restaurant(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
        this.ratings = new ArrayList<>(); 
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public void addRating(int score) {
        if (score >= 1 && score <= 5) {
            ratings.add(score);
            System.out.println("Puanınız için teşekkürler!");
        } else {
            System.out.println("Lütfen 1 ile 5 arasında bir puan veriniz.");
        }
    }
    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int score : ratings) {
            sum += score;
        }
        return (double) sum / ratings.size();
    }

    public void showMenu() {
        double avg = getAverageRating();
        String starDisplay = (avg == 0) ? "Yeni" : String.format("%.1f Yıldız", avg);

        System.out.println("\n--- " + name + " (" + starDisplay + ") Menüsü ---");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }
    }

    public MenuItem getItem(int index) {
        if (index >= 0 && index < menu.size()) {
            return menu.get(index);
        }
        return null;
    }
}