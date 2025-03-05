package com.example.demo.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.demo.Entity.User;
import com.example.demo.Service.EmailService;
import com.example.demo.Service.InactiveService;

import java.util.List;


@Component
public class TaskInactive {

    @Autowired
    private InactiveService inactiveService;
    
    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 8 * * *")
	public void inactiveCheck() {
    List<User> inactiveUser = inactiveService.obterInactiveUser();
    for (User user : inactiveUser) {
        emailService.sendEmailInactive(user.getEmail());
    }
}

}
