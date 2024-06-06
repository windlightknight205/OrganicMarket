package com.project.shopapp.dtos.order;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RevenueDTO {
    private int month;
    private double revenue;

}
