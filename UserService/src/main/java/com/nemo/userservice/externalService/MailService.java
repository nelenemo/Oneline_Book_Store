package com.nemo.userservice.externalService;
import com.nemo.userservice.entity.Mail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name= "MAIL-SERVICE")
public interface MailService {
    @PostMapping("/mails")
    String mail(@RequestBody Mail mail);

}
