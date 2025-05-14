package com.popcorntime.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.popcorntime.dto.AuthenticationRequest;
import com.popcorntime.dto.AuthenticationResponse;
import com.popcorntime.dto.RegisterRequest;
import com.popcorntime.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testRegister() throws Exception {
        RegisterRequest request = RegisterRequest.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .password("securepassword")
                .build();

        AuthenticationResponse response = new AuthenticationResponse("mocked-token");

        when(authenticationService.register(any(RegisterRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mocked-token"));
    }

    @Test
    void testAuthenticate() throws Exception {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("john.doe@example.com")
                .password("securepassword")
                .build();

        AuthenticationResponse response = new AuthenticationResponse("auth-token");

        when(authenticationService.authenticate(any(AuthenticationRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("auth-token"));
    }
}
