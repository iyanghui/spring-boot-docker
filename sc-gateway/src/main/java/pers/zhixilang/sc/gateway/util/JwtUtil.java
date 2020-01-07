package pers.zhixilang.sc.gateway.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * @author zhixilang
 * @version 1.0
 * date 2020-01-02 10:56
 */
@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * 密钥
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 过期时间
     */
    @Value("${jwt.ttl}")
    private long ttlMillis;

    /**
     * 生成jwt
     * @param userId 用户id
     * @param claimInfo 声明信息
     * @return String
     * @author  tanxx
     * date: 18-9-6 下午2:35
     */
    public String createJWT(String userId, Map<String, Object> claimInfo) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setClaims(claimInfo)
                .setSubject(userId)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }

    /**
     * 验证jwt
     * @param jwtStr jwt
     * @return CheckResult
     * @author  tanxx
     * date: 2018/12/5 14:33
     */
    public boolean validateJWT(String jwtStr) {
        try {
            parseJWT(jwtStr);
        } catch (ExpiredJwtException e) {
            logger.error("jwt过期： ", e);
            return false;
        } catch (MalformedJwtException e) {
            logger.error("异常token: [{}], 错误：", jwtStr, e);
            return false;
        }catch (Exception e) {
            logger.error("jwt校验异常： ", e);
            return false;
        }
        return true;
    }

    /**
     * 解析jwt
     * @param jwt jwt
     * @return Claims
     * @author  tanxx
     * date: 2018/12/5 14:35
     * @throws Exception exception
     */
    public Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 获取密钥
     * @return SecretKey
     * @author  tanxx
     * date: 2018/12/5 14:53
     */
    private SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(secret);
        //两个参数，第一个为私钥字节数组，第二个为加密方式 AES或者DES(均为对称加密)
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}
