package com.popcorntime.controller;

import com.popcorntime.dto.MovieDto;
import com.popcorntime.dto.UserDto;
import com.popcorntime.mapper.MovieMapper;
import com.popcorntime.mapper.UserMapper;
import com.popcorntime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MovieMapper movieMapper;
    private final UserMapper userMapper;

    @GetMapping("/towatch")
    public ResponseEntity<List<MovieDto>> getUserToWatchlist() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Integer userId = userService.getUserIdByEmail(email);

        return ResponseEntity.ok(
                userService.getUserToWatchlist(userId)
                        .stream()
                        .map(movieMapper::mapToMovieDto)
                        .toList()
        );
    }

    @GetMapping("/info")
    public ResponseEntity<UserDto> getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Integer userId = userService.getUserIdByEmail(email);

        return ResponseEntity.ok(
                userMapper.mapToUserDto(userService.getUserInfo(userId))
        );
    }

    @GetMapping("/admin")
    public ResponseEntity<Boolean> isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Integer userId = userService.getUserIdByEmail(email);

        return ResponseEntity.ok(
                userService.isAdmin(userId)
        );
    }
}
