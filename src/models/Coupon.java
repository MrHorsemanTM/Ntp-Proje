package models;

public class Coupon {
    private String code;
    private int discountPercentage; 

    public Coupon(String code, int discountPercentage) {
        this.code = code;
        this.discountPercentage = discountPercentage;
    }

    public String getCode() {
        return code;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }
}