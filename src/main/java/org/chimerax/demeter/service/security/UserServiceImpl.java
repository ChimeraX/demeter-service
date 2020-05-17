package org.chimerax.demeter.service.security;

import lombok.AllArgsConstructor;
import org.chimerax.common.security.jwt.JWTService;
import org.chimerax.common.security.jwt.UserDetailsImpl;
import org.chimerax.demeter.entity.User;
import org.chimerax.demeter.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 10-May-20
 * Time: 1:22 PM
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private JWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<User> userOptional = userRepository.findById(username);
        final User user = userOptional.orElseThrow(() -> new UsernameNotFoundException(username));
        return UserDetailsImpl.builder()
                .username(user.getEmail())
                .build();
    }

    @Override
    public String generate(final String code, final String c_token) {

        final UserDetails oauthUser = jwtService.extractJWTUser(c_token);

        final User user = new User()
                .setEmail(oauthUser.getUsername())
                .setCode(code);

        userRepository.save(user);

        final UserDetails userDetails = UserDetailsImpl.builder()
                .username(oauthUser.getUsername())
                .build();

        return jwtService.generateToken(userDetails);
    }
}
