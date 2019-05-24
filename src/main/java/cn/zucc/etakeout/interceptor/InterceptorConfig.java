package cn.zucc.etakeout.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
        registry.addInterceptor(globalInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}