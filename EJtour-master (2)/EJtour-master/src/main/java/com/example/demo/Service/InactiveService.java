package com.example.demo.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InactiveService {

    @Autowired
    private UserRepository userRepository;

    public List<User> obterInactiveUser() { 
        LocalDateTime twoMonthsAgo = LocalDateTime.now().minusMonths(2);
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(user -> user.getLastLogin() == null || user.getLastLogin().isBefore(twoMonthsAgo))
                .collect(Collectors.toList());
    }
}
