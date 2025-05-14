package com.popcorntime.controller;

import com.popcorntime.dto.MovieDto;
import com.popcorntime.dto.UserDto;
import com.popcorntime.mapper.MovieMapper;
import com.popcorntime.mapper.UserMapper;
import com.popcorntime.model.Movie;
import com.popcorntime.model.User;
import com.popcorntime.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private UserController userController;

    private static final String TEST_EMAIL = "test@example.com";
    private static final Integer TEST_USER_ID = 1;

    @BeforeEach
    public void setup() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(TEST_EMAIL);
        SecurityContextHolder.setContext(securityContext);
        when(userService.getUserIdByEmail(TEST_EMAIL)).thenReturn(TEST_USER_ID);
    }

    @Test
    public void testGetUserToWatchlist() {
        Movie movie1 = new Movie();
        movie1.setMovie_id(1);
        movie1.setTitle("Test Movie 1");
        movie1.setDirector("Director 1");
        movie1.setRating(8);

        Movie movie2 = new Movie();
        movie2.setMovie_id(2);
        movie2.setTitle("Test Movie 2");
        movie2.setDirector("Director 2");
        movie2.setRating(9);

        List<Movie> movieList = Arrays.asList(movie1, movie2);

        MovieDto movieDto1 = new MovieDto();
        movieDto1.setMovie_id(1);
        movieDto1.setTitle("Test Movie 1");
        movieDto1.setDirector("Director 1");
        movieDto1.setRating(8);

        MovieDto movieDto2 = new MovieDto();
        movieDto2.setMovie_id(2);
        movieDto2.setTitle("Test Movie 2");
        movieDto2.setDirector("Director 2");
        movieDto2.setRating(9);

        when(userService.getUserToWatchlist(TEST_USER_ID)).thenReturn(movieList);
        when(movieMapper.mapToMovieDto(movie1)).thenReturn(movieDto1);
        when(movieMapper.mapToMovieDto(movie2)).thenReturn(movieDto2);

        ResponseEntity<List<MovieDto>> response = userController.getUserToWatchlist();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());

        boolean foundMovie1 = false;
        boolean foundMovie2 = false;

        for (MovieDto movieDto : response.getBody()) {
            if (movieDto.getMovie_id() == 1 && movieDto.getTitle().equals("Test Movie 1")) {
                foundMovie1 = true;
            }
            if (movieDto.getMovie_id() == 2 && movieDto.getTitle().equals("Test Movie 2")) {
                foundMovie2 = true;
            }
        }

        assertTrue(foundMovie1, "Nie znaleziono movieDto1 w odpowiedzi");
        assertTrue(foundMovie2, "Nie znaleziono movieDto2 w odpowiedzi");

        verify(userService).getUserIdByEmail(TEST_EMAIL);
        verify(userService).getUserToWatchlist(TEST_USER_ID);
        verify(movieMapper, times(2)).mapToMovieDto(any(Movie.class));
    }

    @Test
    public void testGetUserInfo() {
        User user = new User();
        user.setUserId(TEST_USER_ID);
        user.setEmail(TEST_EMAIL);
        user.setName("Test");
        user.setSurname("User");

        UserDto userDto = new UserDto();
        userDto.setUserId(TEST_USER_ID);
        userDto.setEmail(TEST_EMAIL);
        userDto.setName("Test");
        userDto.setSurname("User");

        when(userService.getUserInfo(TEST_USER_ID)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserInfo();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(TEST_USER_ID, response.getBody().getUserId());
        assertEquals(TEST_EMAIL, response.getBody().getEmail());
        assertEquals("Test", response.getBody().getName());
        assertEquals("User", response.getBody().getSurname());

        verify(userService).getUserIdByEmail(TEST_EMAIL);
        verify(userService).getUserInfo(TEST_USER_ID);
        verify(userMapper).mapToUserDto(user);
    }

    @Test
    public void testIsAdmin_whenUserIsAdmin() {
        when(userService.isAdmin(TEST_USER_ID)).thenReturn(true);

        ResponseEntity<Boolean> response = userController.isAdmin();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody());

        verify(userService).getUserIdByEmail(TEST_EMAIL);
        verify(userService).isAdmin(TEST_USER_ID);
    }

    @Test
    public void testIsAdmin_whenUserIsNotAdmin() {
        when(userService.isAdmin(TEST_USER_ID)).thenReturn(false);

        ResponseEntity<Boolean> response = userController.isAdmin();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody());

        verify(userService).getUserIdByEmail(TEST_EMAIL);
        verify(userService).isAdmin(TEST_USER_ID);
    }

    @Test
    public void testGetUserToWatchlist_EmptyWatchlist() {
        when(userService.getUserToWatchlist(TEST_USER_ID)).thenReturn(Collections.emptyList());

        ResponseEntity<List<MovieDto>> response = userController.getUserToWatchlist();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());

        verify(userService).getUserIdByEmail(TEST_EMAIL);
        verify(userService).getUserToWatchlist(TEST_USER_ID);
        verify(movieMapper, never()).mapToMovieDto(any(Movie.class));
    }
}