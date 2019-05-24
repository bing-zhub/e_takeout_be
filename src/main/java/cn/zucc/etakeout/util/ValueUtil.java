package cn.zucc.etakeout.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Date ：Created in 2019/4/30 21:50
 * @Description：值处理工具包
 * @Created By：bing
 */
public class ValueUtil {

    public static final int EXPIRE_TIME = 3 * 60 * 60 * 1000;
    private static final String TOKEN_SECRET = "emh1YmluZzMxNjAyNDAy";

    // 多线程
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer randomValue = random.nextInt(9000000 ) + 1000000;
        Long timestamp = System.currentTimeMillis();
        return timestamp + String.valueOf(randomValue);
    }

    public static String sign(String username, String remote_add) {
        String ret = "";
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            ret = JWT.create()
                    .withHeader(header)
                    .withClaim("name", username)
                    .withClaim("remote_add", remote_add)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static boolean verify(String token, String username, String remote_addr) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            boolean nameMatch = jwt.getClaim("name").asString().equals(username);
            boolean remoteAddrMatch = jwt.getClaim("remote_add").asString().equals(remote_addr);

            return nameMatch && remoteAddrMatch;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean equals(Double d1, Double d2) {
        return Math.abs(d1 - d2) < 0.001;
    }
}
