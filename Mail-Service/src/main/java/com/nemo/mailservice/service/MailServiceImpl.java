package com.nemo.mailservice.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body) {
        try {
            MimeMessage mimeMessage=javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setTo(to);


        }catch(Exception e){
            throw new RuntimeException(e);
        }

        return null;
    }
}
