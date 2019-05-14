package cn.zucc.etakeout.service;

import cn.zucc.etakeout.dto.OrderDTO;

public interface PushMessageService {

    // 订单消息变更
    void orderStatusUpdate(OrderDTO orderDTO);
}
