package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavouriteDTO {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("product_id")
    private String productId;
}
