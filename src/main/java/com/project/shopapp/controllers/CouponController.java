package com.project.shopapp.controllers;

import com.project.shopapp.dtos.category.CategoryDTO;
import com.project.shopapp.dtos.coupon.CouponDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.Category;
import com.project.shopapp.models.Coupon;
import com.project.shopapp.models.CouponCondition;
import com.project.shopapp.responses.ResponseObject;
import com.project.shopapp.responses.category.CategoryResponse;
import com.project.shopapp.responses.coupon.CouponCalculationResponse;
import com.project.shopapp.services.coupon.CouponService;
import com.project.shopapp.utils.MessageKeys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/coupons")
//@Validated
//Dependency Injection
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;
    @GetMapping("")
    public ResponseEntity<List<Coupon>> getAllCoupon(){
        List<Coupon> coupons=couponService.getAllCoupon();
        return ResponseEntity.ok(coupons);
    }
    @GetMapping("/calculate")
    public ResponseEntity<ResponseObject> calculateCouponValue(
            @RequestParam("couponCode") String couponCode,
            @RequestParam("totalAmount") double totalAmount) {
        double finalAmount = couponService.calculateCouponValue(couponCode, totalAmount);
        CouponCalculationResponse couponCalculationResponse = CouponCalculationResponse.builder()
                .result(finalAmount)
                .build();
        return ResponseEntity.ok(new ResponseObject(
                "Calculate coupon successfully",
                HttpStatus.OK,
                couponCalculationResponse
        ));
    }
    @GetMapping("/{couponId}")
    public ResponseEntity<List<CouponCondition>> getCouponConditions(@PathVariable Long couponId) {
        List<CouponCondition> couponCondition =  couponService.getConditionsByCouponId(couponId);
        return ResponseEntity.ok(couponCondition);
    }
    @PostMapping("/add")
    public ResponseEntity<String> createCoupon(@RequestBody CouponDTO couponDTO) {
        couponService.addCouponWithConditions(couponDTO);
        return ResponseEntity.ok("Add coupon successfully");
    }

    @DeleteMapping("/delete/{couponId}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long couponId) throws DataNotFoundException {
        couponService.deleteCoupon(couponId);
        return ResponseEntity.ok("delete successfully");
    }

    @PutMapping("/block/{couponId}/{active}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> blockOrEnable(
            @Valid @PathVariable long couponId,
            @Valid @PathVariable int active
    ) throws Exception {
        couponService.blockOrEnable(couponId, active > 0);
        String message = active > 0 ? "Successfully enabled the coupon." : "Successfully blocked the coupon.";
        return ResponseEntity.ok().body(ResponseObject.builder()
                .message(message)
                .status(HttpStatus.OK)
                .data(null)
                .build());
    }

}
