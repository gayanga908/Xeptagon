package com.test.xeptagon.controllers;

import com.test.xeptagon.repositories.UserRepository;
import com.test.xeptagon.services.JwtUserDetailsService;
import com.test.xeptagon.services.UserService;
import com.test.xeptagon.utils.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Files;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserControllerIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void clearDatabase(@Autowired UserRepository userRepository) {
        userRepository.delete(userRepository.findUserByUserName("James"));
    }

    @Test
    public void should_create_instructor_user() throws Exception {

        UserDetails userDetails = userDetailsService
                .loadUserByUsername("admin");

        final String token = jwtTokenUtil.generateToken(userDetails);

        final File jsonFile = new ClassPathResource("requests/CreateUser.json").getFile();
        final String createUser = Files.readString(jsonFile.toPath());
        this.mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer "+token)
                        .content(createUser))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
