package cn.zucc.etakeout.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Date ：Created in 2019/5/4 11:54
 * @Description：微信支付配置
 * @Created By：bing
 */
@Component
public class WechatPayConfig {

    @Autowired
    private WechatAccountConfig accountConfig;

    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config(){
        WxPayH5Config config = new WxPayH5Config();
        config.setAppId(accountConfig.getMpAppId());
        config.setAppSecret(accountConfig.getMpAppSecret());
        config.setMchId(accountConfig.getMchId());
        config.setMchKey(accountConfig.getMchKey());
        config.setKeyPath(accountConfig.getKeyPath());
        config.setNotifyUrl(accountConfig.getNotifyUrl());
        return config;
    }
}
