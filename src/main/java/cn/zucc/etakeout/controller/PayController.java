package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.exception.SellException;
import cn.zucc.etakeout.mappings.ResultMapping;
import cn.zucc.etakeout.service.OrderService;
import cn.zucc.etakeout.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Date ：Created in 2019/5/4 10:56
 * @Description：订单支付
 * @Created By：bing
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId, @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map){

        OrderDTO order = orderService.findOne(orderId);
        if(order==null){
            throw new SellException(ResultMapping.ORDER_NOT_EXIST);
        }

        PayResponse payResponse = payService.create(order);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);

        return new ModelAndView("pay/create", map);
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);

        //告知微信处理完成 结束异步通知 否则notify会一直有消息进来
        return new ModelAndView("pay/success");
    }

}
