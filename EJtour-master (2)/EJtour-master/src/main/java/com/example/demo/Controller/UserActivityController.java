package main.java.com.example.demo.Controller;

import com.example.demo.Entity.UserActivity;
import com.example.demo.Service.UserActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class UserActivityController {

    private final UserActivityService userActivityService;

    public UserActivityController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

 
    @GetMapping("/{userId}")
    public ResponseEntity<List<UserActivity>> getUserActivities(@PathVariable Long userId) {
        return ResponseEntity.ok(userActivityService.getUserActivities(userId));
    }
}
