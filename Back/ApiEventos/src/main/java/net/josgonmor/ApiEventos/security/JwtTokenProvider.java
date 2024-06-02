package net.josgonmor.ApiEventos.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Autowired
    JwtTokenBlacklist jwtTokenBlacklist;
    private final Key key;
    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(ConstantesSeguridad.JWT_FIRMA.getBytes());
    }
    public String generarToken(Authentication authentication){
        String username = authentication.getName();
        Date tiempoActual = new Date();
        Date expiracionToken = new Date(tiempoActual.getTime() + ConstantesSeguridad.JWT_EXPIRATION_TOKEN);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(tiempoActual)
                .setExpiration(expiracionToken)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String obtenerUserNameDeJwt(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


    public Boolean validarToken(String token){
        if(jwtTokenBlacklist.isTokenBlacklisted(token)){
            throw new AuthenticationCredentialsNotFoundException("EL token que está usando ha sido invalidado");
        }else {
            try {
                Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(ConstantesSeguridad.JWT_FIRMA.getBytes()))
                        .build()
                        .parseClaimsJws(token);
                return true;
            } catch (Exception e) {
                throw new AuthenticationCredentialsNotFoundException("JWT ha expirado o no es correcto", e);
            }
        }
    }
}
