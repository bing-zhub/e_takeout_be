package cn.zucc.etakeout.controller;


import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Value("${wxhost}")
    String baseurl;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String url = baseurl + "/wechat/userInfo";
        String result =  wxMpService.oauth2buildAuthorizationUrl(url,
                WxConsts.OAUTH2_SCOPE_USER_INFO,
                URLEncoder.encode(returnUrl));
        return "redirect:"+ result;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl){
        String openid= "";
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken =  wxMpService.oauth2getAccessToken(code);
            openid =  wxMpOAuth2AccessToken.getOpenId();
        } catch (WxErrorException e) {
            System.out.println(e.getError().getErrorMsg());
            e.printStackTrace();
        }
        return "redirect:"+returnUrl+"?openid="+openid;
    }

}
