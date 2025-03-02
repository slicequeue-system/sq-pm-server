package app.slicequeue.project_manager.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${jwt.expiration-time}")
    private long EXPIRATION_TIME;

    public record JwtTokenResult(String accessToken, Date expiredDate) { }

    public JwtTokenResult generateToken(Long userPk, List<String> roles) {

        Claims claims = Jwts.claims().setSubject(String.valueOf(userPk));
        claims.put("roles", roles);
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + EXPIRATION_TIME);

        String accessToken = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(expiredDate) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret 값 세팅
                .compact();

        return new JwtTokenResult(accessToken, expiredDate);
    }

    public String extractUserPk(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
