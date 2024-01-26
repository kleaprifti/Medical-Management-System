package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.security.JwtTokenUtil;
import com.example.medicalmanagement.service.CustomUserDetailsService;
import com.example.medicalmanagement.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JwtAuthenticationController.class)
class JwtAuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private CustomUserDetailsService userDetailsService;

    @MockBean
    private RememberMeServices rememberMeServices;

    @Test
    void createAuthenticationToken_SuccessfulAuthentication() throws Exception {
//        when(loginService.authenticateUser(anyString(), anyString())).thenReturn(true);
//
//        UserDetails userDetails = mock(UserDetails.class);
//        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
//
//        when(jwtTokenUtil.generateToken(userDetails)).thenReturn("mockToken");
//
//        mockMvc.perform(get("/authenticate/a")
//                        .param("username", "romeisaaliu1@gmail.com")
//                        .param("password", "password")
//                        .param("rememberMe", "true"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").value("mockToken"));
//
//        verify(rememberMeServices, times(1)).loginSuccess(any(), any(), any());
    }

    @Test
    void createAuthenticationToken_UnsuccessfulAuthentication() throws Exception {
        when(loginService.authenticateUser(anyString(), anyString())).thenReturn(false);

        mockMvc.perform(get("/authenticate/a")
                        .param("username", "testUser")
                        .param("password", "incorrectPassword"))
                .andExpect(status().isUnauthorized());

        verify(rememberMeServices, never()).loginSuccess(any(), any(), any());
    }
}
