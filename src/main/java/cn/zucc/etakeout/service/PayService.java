package cn.zucc.etakeout.service;


import cn.zucc.etakeout.dto.OrderDTO;

public interface PayService {

    void create(OrderDTO orderDTO);
}
