package cn.zucc.etakeout.util;

import cn.zucc.etakeout.bean.OrderMaster;
import cn.zucc.etakeout.dto.OrderDTO;
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
}
