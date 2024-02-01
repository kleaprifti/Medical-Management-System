package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.LoginInfoDto;
import com.example.medicalmanagement.security.JwtTokenUtil;
import com.example.medicalmanagement.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final RememberMeServices rememberMeServices;
    private final JwtTokenUtil jwtTokenUtil;
    @Value("${security.jwt.enabled}")
    private boolean jwtEnabled;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController(LoginService loginService,
                           RememberMeServices rememberMeServices,
                           JwtTokenUtil jwtTokenUtil) {
        this.loginService = loginService;
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
            isAuthenticated = handleRememberMeLogin(rememberMe && jwtEnabled, request, response);
        } else {
            isAuthenticated = loginService.authenticateUser(loginInfoDto.getUsername(), loginInfoDto.getPassword());
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("isAuthenticated", isAuthenticated);

        if (isAuthenticated) {
            handleSuccessfulLogin(rememberMe && jwtEnabled, request, response, responseBody);
        } else {
            handleFailedLogin(responseBody);
        }

        return ResponseEntity.status(isAuthenticated ? HttpStatus.OK : HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    private boolean handleRememberMeLogin(boolean rememberMe, HttpServletRequest request, HttpServletResponse response) {
        if (rememberMe) {
            String token = extractTokenFromRequest(request);

            if (StringUtils.hasText(token)) {
                UserDetails userDetails = jwtTokenUtil.extractUserDetails(token);

                if (userDetails != null) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, userDetails.isCredentialsNonExpired(), userDetails.getAuthorities());
                    rememberMeServices.loginSuccess(request, response, authentication);
                    return true;
                } else {
                    handleNullUserDetails();
                }
            }
        }

        return false;
    }

    private void handleSuccessfulLogin(boolean rememberMe, HttpServletRequest request, HttpServletResponse response, Map<String, Object> responseBody) {
        responseBody.put("message", "Login successful");

        if (rememberMe) {
            UserDetails userDetails = getUserDetailsFromToken(request);

            if (userDetails != null) {
                handleRememberMe(request, response);
                responseBody.put("username", userDetails.getUsername());
            } else {
                handleNullUserDetails();
            }
        } else {
            logger.info("RememberMe is false or LoginInfoDto is null");
        }
    }
    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }

    private UserDetails getUserDetailsFromToken(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);

        if (token != null) {
            return jwtTokenUtil.extractUserDetails(token);
        } else {
            logger.info("Token is null");
            return null;
        }
    }


    private void handleRememberMe(HttpServletRequest request, HttpServletResponse response) {
        if (rememberMeServices != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            rememberMeServices.loginSuccess(request, response, authentication);
        } else {
            handleRememberMeServicesNotConfigured();
        }
    }

    public void handleFailedLogin(Map<String, Object> responseBody) {
        responseBody.put("message", "Invalid credentials");
        logger.error("Invalid credentials");
    }

    private void handleRememberMeServicesNotConfigured() {
        logger.error("RememberMeServices is not configured");
    }

    private void handleNullUserDetails() {
        logger.error("UserDetails is null");
    }
}
