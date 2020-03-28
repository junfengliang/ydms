package cn.genlei.ydms.utils;

import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * The type Jwt util.
 * @author nid
 */
@Component
@Slf4j
@Data
public class JwtUtil {


    @Value("${ydms.jwt.secret}")
     String secret;

    /**
     * token expire minutes
     */
    @Value("${ydms.jwt.expire:60}")
     int expire;

    /**
     * 获取token参数
     * @param token
     * @return
     */
    public Claims getClaims(final String token) {
        final Jws<Claims> jws = parse(token);
        return jws == null ? null : jws.getBody();
    }

    /**
     * get subject in token
     *
     * @param token access token
     * @return subject in token
     */
    public String getSubject(final String token) {
        final Claims claims = this.getClaims(token);
        return claims == null ? null : claims.getSubject();
    }

    /**
     * parse token
     * @param token
     * @return
     */
    public Jws<Claims> parse(final String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
        } catch (final SignatureException e) {
            log.debug("Invalid JWT signature", e);
        } catch (final MalformedJwtException e) {
            log.debug("Invalid JWT token", e);
        } catch (final ExpiredJwtException e) {
            log.debug("Expired JWT token", e);
            throw e;
        } catch (final UnsupportedJwtException e) {
            log.debug("Unsupported JWT token");
        } catch (final IllegalArgumentException e) {
            log.debug("JWT token compact of handler are invalid", e);
        }
        return null;
    }

    /**
     * generate token
     *
     * @param subject  id of user
     * @return string string
     */
    public String generate(String subject){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 15);
        Date expireDate = calendar.getTime();
        String uuid = UUID.randomUUID().toString();
        String token = Jwts.builder()
                .setSubject(subject)
                .claim("uuid",uuid)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compressWith(CompressionCodecs.DEFLATE)
                .compact();
        log.debug("token: {}", token);
        return token;
    }
}
