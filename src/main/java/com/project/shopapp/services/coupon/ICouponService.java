package com.project.shopapp.services.coupon;

import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.CouponCondition;

import java.util.List;

public interface ICouponService {
    double calculateCouponValue(String couponCode, double totalAmount);
    List<CouponCondition> getConditionsByCouponId(Long couponId);
    void blockOrEnable(Long couponId, Boolean active) throws DataNotFoundException;
     void deleteCoupon(Long id) throws DataNotFoundException;
}
