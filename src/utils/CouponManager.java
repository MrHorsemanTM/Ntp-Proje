package utils;

import models.Coupon;
import java.util.ArrayList;
import java.util.List;

public class CouponManager {
    private static List<Coupon> coupons = new ArrayList<>();
    static {
    	
        coupons.add(new Coupon("MERHABA10", 10)); 
        coupons.add(new Coupon("YEMEK20", 20));   
        coupons.add(new Coupon("FIRSAT50", 50));  
    }
    
    public static Coupon getCoupon(String code) {
        for (Coupon coupon : coupons) {
            if (coupon.getCode().equalsIgnoreCase(code)) { 
                return coupon;
            }
        }
        return null; // 
    }
}