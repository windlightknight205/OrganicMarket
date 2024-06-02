package com.project.shopapp.dtos.coupon;

import com.project.shopapp.models.CouponCondition;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CouponDTO {
    private String code;
    private boolean active;
    private List<CouponCondition> conditions;

    // Getters and Setters
}

