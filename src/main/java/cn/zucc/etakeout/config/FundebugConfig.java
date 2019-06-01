package cn.zucc.etakeout.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fundebug.Fundebug;
import com.fundebug.SpringConfig;

/**
 * @Date ：Created in 2019/6/1 09:47
 * @Description：
 * @Created By：bing
 */


@Configuration
@Import(SpringConfig.class)
public class FundebugConfig {
    @Bean
    public Fundebug getBean() {
        return new Fundebug("73e127490df763e15a1bda18d21325661beafaa6fbeec3630b1f106b1365266f");
    }
}