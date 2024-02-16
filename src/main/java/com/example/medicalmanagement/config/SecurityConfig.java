package com.example.medicalmanagement.config;
 import com.example.medicalmanagement.security.JwtAuthenticationEntryPoint;
 import com.example.medicalmanagement.security.JwtRequestFilter;
 import com.example.medicalmanagement.security.JwtTokenUtil;
 import com.example.medicalmanagement.service.CustomUserDetailsService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.config.http.SessionCreationPolicy;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.security.web.SecurityFilterChain;
 import org.springframework.security.web.authentication.RememberMeServices;
 import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
 import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
 import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
 import org.springframework.util.StringUtils;
 import org.springframework.web.bind.annotation.CrossOrigin;

 import javax.sql.DataSource;
 import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@CrossOrigin
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    private boolean jwtEnabled = false;

    @Autowired
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(false);
        return tokenRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> {
                    if (jwtEnabled) {
                        authorize
                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated();
                    }else {
                        authorize
                                .requestMatchers("/login").authenticated()
                                .anyRequest().permitAll();
                    }
                })
                .httpBasic(withDefaults());

        if (jwtEnabled) {
            configureJwt(http);
        }

        return http.build();
    }

    private void configureJwt(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(withDefaults())
                .rememberMe(rememberMeConfigurer -> rememberMeConfigurer
                        .key("secret")
                        .tokenValiditySeconds(300)
                        .rememberMeParameter("rememberMe")
                        .userDetailsService(userDetailsService)
                        .tokenRepository(persistentTokenRepository())
                        .authenticationSuccessHandler((request, response, authentication) -> {
                            if (StringUtils.isEmpty(request.getHeader("Authorization"))) {
                                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                                String jwtToken = jwtTokenUtil.generateToken(userDetails);
                                response.setHeader("Authorization", "Bearer " + jwtToken);
                            }
                        })
                );
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        return new PersistentTokenBasedRememberMeServices("secret", userDetailsService, persistentTokenRepository());
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}