package cn.zucc.etakeout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Date ：Created in 2019/5/23 15:46
 * @Description：卖家信息
 * @Created By：bing
 */
@Controller
@RequestMapping("/seller")
public class SellerController {

    @GetMapping("/info")
    public ModelAndView getSellerInfo() {
        return new ModelAndView("mock/seller");
    }
}
