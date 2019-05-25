package cn.zucc.etakeout.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Date ：Created in 2019/5/24 21:46
 * @Description：
 * @Created By：bing
 */
@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    GlobalInterceptor globalInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(globalInterceptor);

        // 客户端创建与微信异步通知
        interceptorRegistration.excludePathPatterns("/pay/*");

        // 客户端获取卖家信息
        interceptorRegistration.excludePathPatterns("/seller/*");

        // 管理端用户登录
        interceptorRegistration.excludePathPatterns("/user/login");

        // 管理端用户注册
        interceptorRegistration.excludePathPatterns("/user/register");

        // 客户端获取商品信息等
        interceptorRegistration.excludePathPatterns("/consumer/*");

        super.addInterceptors(registry);
    }
}