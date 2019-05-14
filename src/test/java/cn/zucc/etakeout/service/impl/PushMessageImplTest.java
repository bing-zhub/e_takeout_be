package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.service.OrderService;
import cn.zucc.etakeout.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PushMessageImplTest {

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatusUpdate() {
        OrderDTO orderDTO = orderService.findOne("15569491129233055328");
        pushMessageService.orderStatusUpdate(orderDTO);
    }
}