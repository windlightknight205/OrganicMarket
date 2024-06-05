package com.project.shopapp.dtos.resetpassword;

import lombok.Data;

@Data
public class MailDTO {
    private String subject;
    private String username;
    private String password;
}
