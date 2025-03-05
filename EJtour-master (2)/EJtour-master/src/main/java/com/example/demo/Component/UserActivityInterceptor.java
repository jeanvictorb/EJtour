package main.java.com.example.demo.Component;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserActivityService;
import com.example.demo.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
public class UserActivityInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;
    private final UserActivityService userActivityService;

    public UserActivityInterceptor(UserRepository userRepository, UserActivityService userActivityService) {
        this.userRepository = userRepository;
        this.userActivityService = userActivityService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String email = request.getHeader("User-Email"); // Captura o e-mail do usuário no Header
        String endpoint = request.getRequestURI();
        String method = request.getMethod();
        String action = method + " " + endpoint; // Exemplo: "POST /users"

        if (email != null) {
            Optional<User> user = userRepository.findByEmail(email);
            user.ifPresent(u -> userActivityService.logActivity(u, action, endpoint));
        }

        return true;
    }
}
