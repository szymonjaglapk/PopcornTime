package com.popcorntime.service;

import com.popcorntime.dto.AuthenticationRequest;
import com.popcorntime.dto.AuthenticationResponse;
import com.popcorntime.dto.RegisterRequest;
import com.popcorntime.model.Type;
import com.popcorntime.model.TypeEnum;
import com.popcorntime.model.User;
import com.popcorntime.repository.TypeRepository;
import com.popcorntime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TypeRepository typeRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .types(new HashSet<>())
                .build();
        // Type type = typeRepository.findByName(TypeEnum.TYPE_USER.name())
        //         .orElse(Type.builder().name(TypeEnum.TYPE_USER.name()).build());
        Type type = typeRepository.findByName(TypeEnum.TYPE_USER.name())
                .orElseGet(() -> typeRepository.save(
                        Type.builder().name(TypeEnum.TYPE_USER.name()).build()
                ));

        user.addType(type);
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}