package cn.zucc.etakeout.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class ValueUtilTest {

    @Test
    public void sign() {
        String token = ValueUtil.sign("admin", "127.0.0.1");
        DecodedJWT jwt = JWT.decode(token);
        boolean match = ValueUtil.verify(token, "admin", "127.0.0.1");
        System.out.println(match);
        match = ValueUtil.verify(token, "admin", "127.0.1.0");
        System.out.println(match);
    }

    @Test
    public void verify() throws UnsupportedEncodingException {
        String plain = "password";
        String cipher = "3db46bd4ee155e48ec6455b8d9dbee58e1499a831ae9c9ad81771d2c7bf7ac50";
        String type = "HmacSHA256";
        String secret = "confidential";
        Algorithm algorithmHS = Algorithm.HMAC256(secret);
        JWTVerifier jwtVerifier = JWT.require(algorithmHS).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(cipher);
        System.out.print(decodedJWT.getPayload());;
    }
}