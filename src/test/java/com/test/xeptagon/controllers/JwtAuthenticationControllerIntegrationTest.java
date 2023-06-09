package com.test.xeptagon.controllers;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File ;
import java.nio.file.Files;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JwtAuthenticationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_jwt_token() throws Exception {

        final File loginCredFile = new ClassPathResource("requests/LoginUser.json").getFile();
        final String loginUser = Files.readString(loginCredFile.toPath());

        this.mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON)
                        .content(loginUser))
                .andDo(print())
                .andExpect(jsonPath("$.jwtToken").exists())
                .andExpect(status().isOk());
    }
}
