package com.project.shopapp.services.coupon;

import com.project.shopapp.dtos.coupon.CouponDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.Coupon;
import com.project.shopapp.models.CouponCondition;
import com.project.shopapp.repositories.CouponConditionRepository;
import com.project.shopapp.repositories.CouponRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CouponService implements ICouponService{
    private final CouponRepository couponRepository;
    private final CouponConditionRepository couponConditionRepository;
    @Override
    public double calculateCouponValue(String couponCode, double totalAmount) {
        Coupon coupon = couponRepository.findByCode(couponCode)
                .orElseThrow(() -> new IllegalArgumentException("Coupon not found"));
        if (!coupon.isActive()) {
            throw new IllegalArgumentException("Coupon is not active");
        }
        double discount = calculateDiscount(coupon, totalAmount);
        double finalAmount = totalAmount - discount;
        return finalAmount;
    }

    @Override
    public List<CouponCondition> getConditionsByCouponId(Long couponId) {
        return couponConditionRepository.findByCouponId(couponId);
    }

    @Override
    public void blockOrEnable(Long couponId, Boolean active) throws DataNotFoundException {
        Coupon existingCoupon = couponRepository.findById(couponId).
                orElseThrow(()->new DataNotFoundException("Data Not Found"));
        existingCoupon.setActive(active);
        couponRepository.save(existingCoupon);
    }

    @Override
    public void deleteCoupon(Long couponId) throws DataNotFoundException {
        Coupon existingCoupon = couponRepository.findById(couponId).
                orElseThrow(()->new DataNotFoundException("Data Not Found"));
        couponRepository.delete(existingCoupon);
    }

    private double calculateDiscount(Coupon coupon, double totalAmount) {
        List<CouponCondition> conditions = couponConditionRepository
                .findByCouponId(coupon.getId());
        double discount = 0.0;
        double updatedTotalAmount = totalAmount;
        for (CouponCondition condition : conditions) {
            //EAV(Entity - Attribute - Value) Model
            String attribute = condition.getAttribute();
            String operator = condition.getOperator();
            String value = condition.getValue();

            double percentDiscount = Double.valueOf(
                    String.valueOf(condition.getDiscountAmount()));

            if (attribute.equals("minimum_amount")) {
                if (operator.equals(">") && updatedTotalAmount > Double.parseDouble(value)) {
                    discount += updatedTotalAmount * percentDiscount / 100;
                }
            } else if (attribute.equals("applicable_date")) {
                LocalDate applicableDate = LocalDate.parse(value);
                LocalDate currentDate = LocalDate.now();
                if (operator.equalsIgnoreCase("BETWEEN")
                        && currentDate.isEqual(applicableDate)) {
                    discount += updatedTotalAmount * percentDiscount / 100;
                }
            }
            //còn nhiều nhiều điều kiện khác nữa
            updatedTotalAmount = updatedTotalAmount - discount;
        }
        return discount;
    }

    @Transactional
    public void addCouponWithConditions(CouponDTO couponDTO) {
        // Create the coupon entity
        Coupon coupon = Coupon.builder()
                .code(couponDTO.getCode())
                .active(couponDTO.isActive())
                .build();
        // Save the coupon to generate its ID
        Coupon savedCoupon = couponRepository.save(coupon);
        // Create and save the conditions
        for (CouponCondition couponCondition : couponDTO.getConditions()) {
            couponCondition.setCoupon(savedCoupon);  // Set the coupon reference in the condition
            couponConditionRepository.save(couponCondition);
        }
    }

    public List<Coupon> getAllCoupon(){
        return couponRepository.findAll();
    }

}
