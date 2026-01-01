package models;

public class MenuItem {
    private String name;
    private double price;
    private String category;

    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " - " + price + " TL (" + category + ")";
    }
}
