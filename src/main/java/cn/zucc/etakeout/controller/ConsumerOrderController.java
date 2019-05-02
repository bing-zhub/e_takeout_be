package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.exception.SellException;
import cn.zucc.etakeout.form.OrderForm;
import cn.zucc.etakeout.mappings.ResultMapping;
import cn.zucc.etakeout.service.OrderService;
import cn.zucc.etakeout.util.Converter;
import cn.zucc.etakeout.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Date ：Created in 2019/5/2 19:31
 * @Description：处理买家订单接口
 * @Created By：bing
 */
@RestController
@RequestMapping("/consumer/order")
public class ConsumerOrderController {

    @Autowired
    OrderService orderService;

    // 创建订单
    @PostMapping("/create")
    public RootData<Map<String, String>> create(@RequestBody OrderForm orderForm){

        OrderDTO orderDTO = Converter.convert(orderForm);
        if(orderDTO.getOrderDetails().isEmpty()){
            throw new SellException(ResultMapping.CART_IS_EMPTY);
        }
        OrderDTO createdOrder = orderService.create(orderDTO);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("orderId", createdOrder.getOrderId());
        return ResultUtil.success(map);
    }

    // 订单列表

    // 订单详情

    // 取消订单

    // 支付订单
}
