package com.popcorntime.config;

import com.popcorntime.model.Type;
import com.popcorntime.model.TypeEnum;
import com.popcorntime.model.User;
import com.popcorntime.repository.TypeRepository;
import com.popcorntime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TypeRepository typeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        System.out.println("✅ DataInitializer działa!");

        Type userType = typeRepository.findByName(TypeEnum.TYPE_USER.name())
                .orElseGet(() -> typeRepository.save(Type.builder().name(TypeEnum.TYPE_USER.name()).build()));
        Type adminType = typeRepository.findByName(TypeEnum.TYPE_ADMIN.name())
                .orElseGet(() -> typeRepository.save(Type.builder().name(TypeEnum.TYPE_ADMIN.name()).build()));

        if (userRepository.findByEmail("test@popcorntime.com").isEmpty()) {
            User testUser = User.builder()
                    .name("Test")
                    .surname("User")
                    .email("test@popcorntime.com")
                    .password(passwordEncoder.encode("test123"))
                    .types(new HashSet<>())
                    .build();
            testUser.addType(userType);
            userRepository.save(testUser);
            System.out.println("✅ Dodano użytkownika testowego");
        }

        if (userRepository.findByEmail("admin@popcorntime.com").isEmpty()) {
            User adminUser = User.builder()
                    .name("Admin")
                    .surname("Popcorn")
                    .email("admin@popcorntime.com")
                    .password(passwordEncoder.encode("admin123"))
                    .types(new HashSet<>())
                    .build();
            adminUser.addType(adminType);
            userRepository.save(adminUser);
            System.out.println("✅ Dodano użytkownika administratora");
        }
    }
}
