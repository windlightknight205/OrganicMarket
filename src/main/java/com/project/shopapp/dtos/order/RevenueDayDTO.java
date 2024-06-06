package com.project.shopapp.dtos.order;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RevenueDayDTO {
    private int day;
    private double revenue;

}
