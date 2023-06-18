package com.nemo.mailservice.controller;

import com.nemo.mailservice.entity.Mail;
import com.nemo.mailservice.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }


    /*@PostMapping("/sendmail")
    public String sendMail(@RequestParam(value = "file", required=false)MultipartFile[] file, String to, String[] cc,String subject,String body){
        return mailService.sendMail(file, to,cc,subject,body);
    }*/
    @PostMapping("/mails")
    public String mail(@RequestBody Mail mail) throws MessagingException {
        return mailService.sendthemail(mail);
    }
}
