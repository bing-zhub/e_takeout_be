package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.service.OrderService;
import cn.zucc.etakeout.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

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
}