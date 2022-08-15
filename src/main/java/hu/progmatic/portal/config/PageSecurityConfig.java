package hu.progmatic.webpage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// https://www.baeldung.com/spring-security-expressions
// https://bushansirgur.in/everything-need-to-know-about-matchers-methods-in-spring-security/
// https://www.baeldung.com/spring-security-granted-authority-vs-role
@Configuration
@EnableWebSecurity
public class PageSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

    }

    // "factoryval" létrehozott @Component
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}