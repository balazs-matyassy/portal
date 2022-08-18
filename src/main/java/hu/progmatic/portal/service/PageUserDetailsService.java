package hu.progmatic.portal.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageUserDetailsService implements UserDetailsService {
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(
                username,
                encoder.encode("password"),
                List.of(
                        new SimpleGrantedAuthority("ROLE_USER"),
                        new SimpleGrantedAuthority("ROLE_ADMIN")
                )
        );

        /* switch (username) {
            case "admin":
                return new User(
                        "admin",
                        encoder.encode("password"),
                        List.of(
                                new SimpleGrantedAuthority("ROLE_USER"),
                                new SimpleGrantedAuthority("ROLE_ADMIN")
                        )
                );
            case "user":
                return new User(
                        "user",
                        encoder.encode("password"),
                        List.of(
                                new SimpleGrantedAuthority("ROLE_USER")
                        )
                );
        }

        throw new UsernameNotFoundException("Username not found."); */
    }
}
