package cn.zucc.etakeout.interceptor;

import cn.zucc.etakeout.exception.SellException;
import cn.zucc.etakeout.mappings.ResultMapping;
import cn.zucc.etakeout.util.ValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Date ：Created in 2019/5/24 21:33
 * @Description：全局拦截器
 * @Created By：bing
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {

    private Long start;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String url;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        url = request.getServletPath();
        start = System.currentTimeMillis();
        String token = request.getHeader("X-Token");
        if(token == null || token.isEmpty())
            throw new SellException(ResultMapping.TOKEN_NOT_FOUND);
        String info = stringRedisTemplate.opsForValue().get(token);
        String remoteAddr = info.split(" ")[0];
        if(remoteAddr == null || remoteAddr.isEmpty() || !remoteAddr.equals(request.getRemoteAddr()))
            throw new SellException(ResultMapping.TOKEN_NOT_MATCH);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Long diff = System.currentTimeMillis() - start;
        System.out.println("["+ request.getMethod() +" "+ url + "]: cost " + diff +"ms");
    }
}
