package com.repipa.css.security.JWT;

import com.repipa.css.security.entity.UsuarioPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    private static final String AUTHORITIES_KEY = "auth";
    private static final String PERMISSIONS_KEY = "permissions";

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;

    @Value("${jwt.secret}")
    private String secret;

    @PostConstruct
    public void init() {

        this.tokenValidityInMilliseconds = 1000 * 12 * 60 * 60; // 12 Horas
        this.tokenValidityInMillisecondsForRememberMe = (long) 1000 * 30 * 24 * 60 * 60; // 1 Mes
    }

    public String generateToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }

        CustomSecurityUser userSecurity = (CustomSecurityUser) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(PERMISSIONS_KEY, userSecurity.getPermissions());
        claims.put(AUTHORITIES_KEY, authorities);

        return createToken(authentication.getName(), claims, validity);
    }

    public String createToken(String subject, Map<String, Object> claims) {
        Date validity = new Date((new Date()).getTime() + this.tokenValidityInMillisecondsForRememberMe);
        return createToken(subject, claims, validity);
    }

    public String createToken(String subject, Map<String, Object> claims, Date validity) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.encode(secret)).setExpiration(validity).compact();
    }

    public String getNombreUsuarioFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token mal formado " +e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("token no soportado " +e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("token expirado " +e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("token vacío " +e.getMessage());
        } catch (SignatureException e) {
            logger.error("error en la firma " +e.getMessage());
        }
        return false;
    }
}
