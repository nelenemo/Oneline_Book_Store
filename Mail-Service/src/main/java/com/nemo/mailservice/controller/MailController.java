package com.nemo.mailservice.controller;

import com.nemo.mailservice.service.MailService;
import jakarta.mail.Multipart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }


    @PostMapping("/sendmail")
    public String sendMail(@RequestParam(value = "file", required=false)MultipartFile[] file, String to, String[] cc,String subject,String body){
        return mailService.sendMail(file, to,cc,subject,body);
    }
}
