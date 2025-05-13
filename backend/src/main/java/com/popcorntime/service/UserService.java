package com.popcorntime.service;

import com.popcorntime.model.Movie;
import com.popcorntime.model.User;
import com.popcorntime.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public int getUserIdByEmail(String email) {
        return userRepository.findUserIdByEmail(email);
    }
    public List<Movie> getUserToWatchlist(Integer userId) {
        return userRepository.getUserwatchlist(userId);
    }

    public User getUserInfo(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Boolean isAdmin(Integer userId) {
        return userRepository.isAdmin(userId);
    }
}

