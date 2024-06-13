package capstone.markproject.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


/*
    -Configures authentication and authorization rules for different URL patterns in the application using Spring Security.
    -Password Encoding: Defines a PasswordEncoder bean (BCryptPasswordEncoder) to securely encode and verify passwords.
    -CSRF Protection: Configures CSRF protection to prevent Cross-Site Request Forgery attacks using cookies.
    -Logout Configuration: Customizes logout behavior to allow all users to log out securely.
    (need to create a log out button for user)
 */
@Configuration
public class WebSecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.warn("Websecurity config password");
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/marks", "/marks/new", "/uploadimage", "/uploads/**","/login", "/user/register","/register").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/user/**").hasRole("USER")
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login") // Customize your login page URL
                                .permitAll()
                )
                .csrf(csrf ->
                        csrf
                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .logout(LogoutConfigurer::permitAll
                );
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        // This is just an example, you should retrieve users from your database or another source
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//        return manager;
//    }
}
