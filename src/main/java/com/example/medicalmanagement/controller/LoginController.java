package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.LoginInfoDto;
import com.example.medicalmanagement.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    private final RememberMeServices rememberMeServices;

    public LoginController(RememberMeServices rememberMeServices) {
        this.rememberMeServices = rememberMeServices;
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginInfoDto loginInfoDto,
                                                     @RequestParam(value = "rememberMe", defaultValue = "false") boolean rememberMe, HttpServletRequest request,
                                                     HttpServletResponse response) {
        boolean isAuthenticated = loginService.authenticateUser(loginInfoDto.getUsername(), loginInfoDto.getPassword());

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("isAuthenticated", isAuthenticated);

        if (isAuthenticated) {
            handleSuccessfulLogin(loginInfoDto, rememberMe, request,response, responseBody);
        } else {
            handleFailedLogin(responseBody);
        }

        return ResponseEntity.status(isAuthenticated ? HttpStatus.OK : HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    private void handleSuccessfulLogin(LoginInfoDto loginInfoDto, boolean rememberMe, HttpServletRequest request, HttpServletResponse response, Map<String, Object> responseBody) {
        responseBody.put("username", loginInfoDto.getUsername());
        responseBody.put("message", "Login successful");

        if (rememberMe) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            rememberMeServices.loginSuccess(request, response, authentication);
        }
    }

    private void handleFailedLogin(Map<String, Object> responseBody) {
        responseBody.put("message", "Invalid credentials");
    }
}