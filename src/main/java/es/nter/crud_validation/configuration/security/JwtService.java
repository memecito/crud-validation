package es.nter.crud_validation.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Getter
    @Value("${security.jwt.access-token-expiration}")
    private String accessTokenExpiration;

    @Getter
    @Value("${security.jwt.refresh-token-expiration}")
    private String refreshTokenExpiration;

    @Getter
    @Value("${security.jwt.secure-access")
    private boolean secureAccess;

    private final static String REFRESH_TOKE_CLAIM = "refresh_token";
    private final static String ACCESS_TOKEN_CLAIM = "accest_token";

    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, accessTokenExpiration, REFRESH_TOKE_CLAIM);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(userDetails, refreshTokenExpiration, REFRESH_TOKE_CLAIM);
    }

    public String generateToken(UserDetails userDetails, String longExpirationTime, String accessType) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities());
        claims.put("access_type", accessType);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt((Date.from(Instant.now())))
                .setExpiration(Date.from(Instant.now().plusMillis(Long.parseLong(longExpirationTime))))
                .signWith(getSignInKey())
                .addClaims(claims)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValidAccessToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token)
                && isTokenOfType(token, ACCESS_TOKEN_CLAIM);
    }

    public boolean isValidRefreshToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token)
                && isTokenOfType(token, REFRESH_TOKE_CLAIM);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private boolean isTokenOfType(String token, String expecteType) {
        String actualType = extractClaim(token, claims -> claims.get("acces_type", String.class));
        return expecteType.equals(actualType);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = estractAllClaims(token);
        return claimResolver.apply(claims);
    }


    private Claims estractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}



