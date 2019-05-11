package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.mappings.OrderStatusMapping;
import cn.zucc.etakeout.mappings.PayStatusMapping;
import cn.zucc.etakeout.service.OrderService;
import cn.zucc.etakeout.service.PayService;
import com.lly835.bestpay.model.RefundResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("15569491129233055328");
        payService.create(orderDTO);
    }

    @Test
    @Transactional
    public void cancel() {
        OrderDTO one = orderService.findOne("15571908637436601921");
        OrderDTO cancel = orderService.cancel(one);
        Assert.assertTrue(cancel.getOrderStatus() == OrderStatusMapping.CANCELED.getCode());
    }

    @Test
    @Transactional
    public void pay() {
        OrderDTO one = orderService.findOne("15571908637436601921");
        OrderDTO pay = orderService.pay(one);
        Assert.assertTrue(pay.getPayStatus() == PayStatusMapping.PAID.getCode());
    }

    @Test
    public void refund(){
        OrderDTO one = orderService.findOne("15571900804033022701");
        RefundResponse refund = payService.refund(one);
        Assert.assertTrue(refund.getOrderAmount() != 0);
    }
}