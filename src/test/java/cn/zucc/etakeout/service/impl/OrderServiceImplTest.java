package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.bean.OrderDetail;
import cn.zucc.etakeout.dto.OrderDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl orderService;

    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setConsumerAddress("111");
        orderDTO.setConsumerName("12313");
        orderDTO.setConsumerOpenid("1231");
        orderDTO.setConsumerPhone("123123");
        orderDTO.setSellerId("1231");

        List<OrderDetail> orderDetails = new LinkedList<>();

        OrderDetail o = new OrderDetail();
        o.setProductId(1);
        o.setProductQuantity(10);
        orderDetails.add(o);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId(123);
        o2.setProductQuantity(5);
        orderDetails.add(o2);

        orderDTO.setOrderDetails(orderDetails);

        orderService.create(orderDTO);

    }

    @Test
    public void findOne() {
        OrderDTO one = orderService.findOne("15567143211395073723");
        Assert.assertNotNull(one);
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDTO> list = orderService.findList("1231", request);
        Assert.assertNotNull(list);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne("15567143211395073723");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}