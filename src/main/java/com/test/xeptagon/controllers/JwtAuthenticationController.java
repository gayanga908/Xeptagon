package com.test.xeptagon.controllers;

import com.test.xeptagon.models.JwtRequest;
import com.test.xeptagon.models.JwtResponse;
import com.test.xeptagon.services.JwtUserDetailsService;
import com.test.xeptagon.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Authentication", description = "Authentication APIs")
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    public JwtAuthenticationController(JwtTokenUtil jwtTokenUtil,
                                       JwtUserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        userDetailsService.authenticateUser(authenticationRequest.getUserName(),
                authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUserName());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
