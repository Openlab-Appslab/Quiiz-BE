package com.example.demo.score;

import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private final UserService userService;

    public ScoreController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ScoreDto getScore() {
        User user = this.getCurrentUser();
        long score = user.getScore();
        return new ScoreDto(score);
    }

    @PostMapping
    public void setScore(@RequestBody ScoreDto dto) {
        User user = this.getCurrentUser();
        user.setScore(dto.getScore());
        this.userService.updateUser(user);
    }

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        return this.userService.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

}
