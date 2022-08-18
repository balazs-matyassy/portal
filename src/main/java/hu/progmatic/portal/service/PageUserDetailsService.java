package hu.progmatic.portal.service;

import hu.progmatic.portal.model.User;
import hu.progmatic.portal.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PageUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public PageUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<hu.progmatic.portal.model.User> userOptional = userRepository.findByUsername(username);
        hu.progmatic.portal.model.User userData = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));

        if (userData.isAdmin()) {
            return new org.springframework.security.core.userdetails.User(
                    userData.getUsername(),
                    userData.getPassword(),
                    List.of(
                            new SimpleGrantedAuthority("ROLE_USER"),
                            new SimpleGrantedAuthority("ROLE_ADMIN")
                    )
            );
        } else {
            return new org.springframework.security.core.userdetails.User(
                    userData.getUsername(),
                    userData.getPassword(),
                    List.of(
                            new SimpleGrantedAuthority("ROLE_USER")
                    )
            );
        }

        /* return new User(
                username,
                encoder.encode("password"),
                List.of(
                        new SimpleGrantedAuthority("ROLE_USER"),
                        new SimpleGrantedAuthority("ROLE_ADMIN")
                )
        ); */

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
