package cn.zucc.etakeout.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

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
    public void verify() {
    }
}