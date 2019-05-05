package cn.zucc.etakeout.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Date ：Created in 2019/5/5 14:25
 * @Description：七牛配置项
 * @Created By：bing
 */
@Data
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiniuClientConfig {
    private String ak;
    private String sk;
    private String bulkName;
}
