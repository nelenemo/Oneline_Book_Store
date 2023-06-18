package com.nemo.mailservice.service;

import com.nemo.mailservice.entity.Mail;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

public interface MailService{
//    String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body);

    String sendthemail(Mail mail) throws MessagingException;
}
