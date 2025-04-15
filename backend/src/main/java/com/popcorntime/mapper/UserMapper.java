package com.popcorntime.mapper;

import com.popcorntime.dto.UserDto;
import com.popcorntime.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getEmail(),
                user.getName(),
                user.getSurname()
        );
    }
}
