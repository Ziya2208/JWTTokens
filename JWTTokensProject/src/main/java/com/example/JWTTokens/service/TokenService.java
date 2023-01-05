package com.example.JWTTokens.service;

import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final JwtEncoder encoder;

    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

   public String generateToken(Authentication authenticaton){
        Instant now = Instant.now();
        String scope = authenticaton.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
       JwtClaimsSet claims = JwtClaimsSet.builder()
               .issuer("self")
               .issuedAt(now)
               .expiresAt(now.plus(1, ChronoUnit.HOURS))
               .subject(authenticaton.getName())
               .claim("scope",scope)
               .build();
       return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
   }
}
