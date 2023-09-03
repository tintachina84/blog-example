package com.tintachina.blog.config.jwt;

import com.tintachina.blog.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    /**
     * 토큰 생성
     * @param expiry
     * @param user
     * @return
     */
    private String makeToken(Date expiry, User user) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 typ: JWT
                .setIssuer(jwtProperties.getIssuer()) // 내용 iss: tintachina84@gmail.com (properties 파일에서 설정한 값)
                .setIssuedAt(now) // 내용 iat: 토큰 발행 시간
                .setExpiration(expiry) // 내용 exp: 토큰 만료 시간
                .setSubject(user.getEmail()) // 내용 sub: 토큰 제목
                .claim("id", user.getId()) // 내용 payload: 토큰 내용, 키 id, 값 user.getId()
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) // 서명: 비밀키를 이용 해시값을 HS256 방식으로 암호화
                .compact();
    }

    /**
     * 토큰 유효성 검사
     * @param token
     * @return
     */
    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey()) // 비밀키를 이용해 토큰을 검증
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) { // 예외가 발생하면 유효하지 않은 토큰으로 판단
            return false;
        }
    }

    /**
     * 토큰으로 인증 정보 조회
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject
                (), "", authorities), token, authorities);
    }

    /**
     * 토큰으로 사용자 아이디 조회
     * @param token
     * @return
     */
    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    /**
     * 토큰을 복호화 하여 클레임(본문) 조회
     * @param token
     * @return
     */
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
