package cn.zucc.etakeout.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    // 公众平台
    private String mpAppId;
    private String mpAppSecret;

    // 微信支付
    private String mchId;
    private String mchKey;
    private String keyPath;
    private String notifyUrl;

    // 公众平台
    private Map<String, String> templateId;
}
