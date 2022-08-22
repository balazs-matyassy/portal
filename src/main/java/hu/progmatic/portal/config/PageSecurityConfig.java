package hu.progmatic.portal.config;

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
        // .antMatchers("/", "/pages/public")
        // .antMatchers("/", "/p*")
        // * szegmens végéig érvényes
        // -> /profil ILLESZKEDIK
        // -> /pages/public NEM illeszkedik
        // .antMatchers("/", "/pages/**"): útvonal végéig érvényes
        // ** nulla vagy több könyvtárat fed le
        // -> /pages ILLESZKEDIK
        // -> /pages/public ILLESZKEDIK
        // -> /pages/public ILLESZKEDIK

        // permitAll() -> BÁRKI MEGNÉZHEZI
        // authenticated() -> MINDEN BEJELENTKEZETT FELHASZNÁLÓ
        // hasRole("ADMIN") -> MINDEN "ADMIN" SZEREPPEL RENDELKEZŐ FELHASZNÁLÜ
        // denyAll() -> SENKI NEM NÉZHETI MEG

        // whitelist: alapértelmezetten semmit nem szabad
        // .anyRequest().denyAll()
        // kivételként adunk hozzá minden olyan útvonalat,
        // amit elérhetővé szeretnénk tenni
        // - nyilvános oldalalakat a permitAll() segítségével
        // - védett oldalakat a hasRole("ADMIN") segítségével

        // blacklist: alapértelmezett minden szabad
        // .anyRequest().permitAll()
        // kivételként adjuk hozzá a védett útvonalakat
        // .antMatcher("/admin").hasRole("ADMIN")

        http.authorizeRequests()
                .antMatchers("/", "/p*/**")
                .permitAll()

                .antMatchers("/admin")
                .hasRole("ADMIN")

                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login-error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
    }

    /* @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        final String password = "1234";
        System.out.println("Hashed form of " + password + " is:\n" + encoder().encode(password));

        auth.inMemoryAuthentication()
                .withUser("user")
                .password(encoder().encode(password))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(encoder().encode(password))
                .roles("USER", "ADMIN")
        ;
    } */

    // "factoryval" létrehozott @Component
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
