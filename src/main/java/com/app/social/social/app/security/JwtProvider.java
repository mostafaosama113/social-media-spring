package com.app.social.social.app.security;

import com.app.social.social.app.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtEncoder jwtEncoder;
    public String createToken(Authentication authentication) {
        Instant now = Instant.now();
        long expireIn = 30; //30 days

        String authorities = authentication.getAuthorities().stream()
                .map(
                        grantedAuthority -> grantedAuthority.getAuthority()
                ).collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet
                .builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(expireIn , ChronoUnit.DAYS))
                .subject(authentication.getName())
                .claim("authorities" , authorities)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
