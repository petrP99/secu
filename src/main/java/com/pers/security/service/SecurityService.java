package com.pers.security.service;

import com.pers.security.repository.UserRepository;
import com.pers.security.security.TokenDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Mono<TokenDetails> authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .flatMap(user -> {
                    if (!user.isEnabled()) {
                        return Mono.error(new RuntimeException(""));
                    }

                    if (!passwordEncoder.matches(password, user.getPassword())) {
                        return Mono.error(new RuntimeException(""));
                    }
                    return Mono.just(new TokenDetails());
                })
                .switchIfEmpty(Mono.error(new RuntimeException("")));
    }
}

