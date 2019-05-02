package cn.zucc.etakeout.util;

import cn.zucc.etakeout.bean.OrderMaster;
import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.form.OrderForm;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @Date ：Created in 2019/5/1 21:50
 * @Description：转化器
 * @Created By：bing
 */
public class Converter {

    public static List<OrderDTO> convert(List<OrderMaster> orderMasters){
        List<OrderDTO> orderDTOS = new LinkedList<>();
        for(OrderMaster orderMaster: orderMasters){
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            orderDTOS.add(orderDTO);
        }

        return orderDTOS;
    }

//    {
//        "name": "Bing",
//            "phone": "18868822111",
//            "address": "Hangzhou, China",
//            "openId": "ew3euwhd7sjw9diwkq",
//            "items": [
//        {
//            "productId": "1",
//                "productQuantity": 2
//        }
//    ]
//    }
    public static OrderDTO convert(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setSellerId(orderForm.getSellerId());
        orderDTO.setConsumerName(orderForm.getName());
        orderDTO.setConsumerPhone(orderForm.getPhone());
        orderDTO.setConsumerAddress(orderForm.getAddress());
        orderDTO.setConsumerOpenid(orderForm.getOpenId());
        orderDTO.setOrderDetails(orderForm.getItems());

        return  orderDTO;
    }
}
