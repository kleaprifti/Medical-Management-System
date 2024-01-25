package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.LoginInfoDto;
import com.example.medicalmanagement.security.JwtTokenUtil;
import com.example.medicalmanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    private final LoginService loginService;
    private final UserDetailsService userDetailsService;
    private final RememberMeServices rememberMeServices;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public LoginController(LoginService loginService,
                           UserDetailsService userDetailsService,
                           RememberMeServices rememberMeServices,
                           JwtTokenUtil jwtTokenUtil) {
        this.loginService = loginService;
        this.userDetailsService = userDetailsService;
        this.rememberMeServices = rememberMeServices;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> login(@RequestBody(required = false) LoginInfoDto loginInfoDto,
                                                     @RequestParam(value = "rememberMe", defaultValue = "false") boolean rememberMe,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) {
        boolean isAuthenticated;

        if (loginInfoDto == null || StringUtils.isEmpty(loginInfoDto.getUsername()) || StringUtils.isEmpty(loginInfoDto.getPassword())) {
            isAuthenticated = handleRememberMeLogin(rememberMe, request, response);
        } else {
            isAuthenticated = loginService.authenticateUser(loginInfoDto.getUsername(), loginInfoDto.getPassword());
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("isAuthenticated", isAuthenticated);

        if (isAuthenticated) {
            handleSuccessfulLogin(loginInfoDto, rememberMe, request, response, responseBody);
        } else {
            handleFailedLogin(responseBody);
        }

        return ResponseEntity.status(isAuthenticated ? HttpStatus.OK : HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    private void handleSuccessfulLogin(LoginInfoDto loginInfoDto, boolean rememberMe, HttpServletRequest request, HttpServletResponse response, Map<String, Object> responseBody) {
        responseBody.put("message", "Login successful");

        if (rememberMe) {
            UserDetails userDetails = getUserDetailsFromToken(request);

            if (userDetails != null) {
                String token = generateTokenAndSetCookie(userDetails, response);
                handleRememberMe(request, response);
                responseBody.put("username", userDetails.getUsername());
//                responseBody.put("token", token); // Include the token in the response
            } else {
                handleNullUserDetails();
            }
        } else {
            System.err.println("RememberMe is false or LoginInfoDto is null");
        }
    }

    private boolean handleRememberMeLogin(boolean rememberMe, HttpServletRequest request, HttpServletResponse response) {
        if (rememberMe) {
            UserDetails userDetails = getUserDetailsFromToken(request);

            if (userDetails != null) {
                String token = generateTokenAndSetCookie(userDetails, response);
                handleRememberMe(request, response);
                return true;
            }
        }

        return false;
    }

    private UserDetails getUserDetailsFromToken(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);

        if (token != null) {
            return jwtTokenUtil.extractUserDetails(token);
        } else {
            System.err.println("Token is null");
            return null;
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }

    private String generateTokenAndSetCookie(UserDetails userDetails, HttpServletResponse response) {
        String token = jwtTokenUtil.generateToken(userDetails);
//        response.addCookie(new Cookie("jwtToken", token));
        return token;
    }

    private void handleRememberMe(HttpServletRequest request, HttpServletResponse response) {
        if (rememberMeServices != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            rememberMeServices.loginSuccess(request, response, authentication);
        } else {
            handleRememberMeServicesNotConfigured();
        }
    }

    private void handleFailedLogin(Map<String, Object> responseBody) {
        responseBody.put("message", "Invalid credentials");
    }

    private void handleRememberMeServicesNotConfigured() {
        System.err.println("RememberMeServices is not configured");
    }

    private void handleNullUserDetails() {
        System.err.println("UserDetails is null");
    }
}