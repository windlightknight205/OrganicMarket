package com.project.shopapp.dtos.resetpassword;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResetDTO {
    private String username;
    private String password;
}