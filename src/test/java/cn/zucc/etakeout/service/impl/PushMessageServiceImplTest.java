package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.service.OrderService;
import cn.zucc.etakeout.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PushMessageServiceImplTest {

    @Autowired
    OrderService orderService;

    @Autowired
    PushMessageService pushMessageService;

    @Test
    public void orderStatusUpdate() {
        OrderDTO one = orderService.findOne("15590162430929409064");
        pushMessageService.orderStatusUpdate(one);
    }

}