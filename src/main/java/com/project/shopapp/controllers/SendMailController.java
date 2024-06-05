package com.project.shopapp.controllers;


import com.project.shopapp.dtos.resetpassword.MailDTO;
import com.project.shopapp.services.sendmail.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}")
public class SendMailController {
    @Autowired
    SendMailService sendMailService;
    @PostMapping("/send/{email}")
    public String sendMail(@PathVariable String email, @RequestBody MailDTO mailDTO){
        sendMailService.sendMail(email, mailDTO);
        return "Send successfully!";
    }
}