package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.bean.OrderDetail;
import cn.zucc.etakeout.bean.OrderMaster;
import cn.zucc.etakeout.bean.ProductInfo;
import cn.zucc.etakeout.dao.OrderDetailDAO;
import cn.zucc.etakeout.dao.OrderMasterDAO;
import cn.zucc.etakeout.dto.CartDTO;
import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.exception.SellException;
import cn.zucc.etakeout.mappings.OrderStatusMapping;
import cn.zucc.etakeout.mappings.PayStatusMapping;
import cn.zucc.etakeout.mappings.ResultMapping;
import cn.zucc.etakeout.service.ProductInfoService;
import cn.zucc.etakeout.service.OrderService;
import cn.zucc.etakeout.util.Converter;
import cn.zucc.etakeout.util.ValueUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * @Date ：Created in 2019/4/30 21:35
 * @Description：OrderService实现类
 * @Created By：bing
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Autowired
    private OrderMasterDAO orderMasterDAO;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        BigDecimal totalPrice = new BigDecimal(0);
        String orderId = ValueUtil.genUniqueKey();
        List<CartDTO> cartDTOList = new LinkedList<>();


        // 数据校验 数量 价格等
        for(OrderDetail orderDetail : orderDTO.getOrderDetails()){
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultMapping.PRODUCT_NOT_EXIST);
            }

            // 计算总价
            BigDecimal multiply = productInfo.getProductPrice().multiply(BigDecimal.valueOf(orderDetail.getProductQuantity()));
            totalPrice =  totalPrice.add(multiply);

            // orderDetail
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setDetailId(ValueUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailDAO.save(orderDetail);

            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }


        // orderMaster
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(totalPrice);
        orderMaster.setOrderStatus(OrderStatusMapping.NEW.getCode());
        orderMaster.setPayStatus(PayStatusMapping.PENDING.getCode());
        OrderMaster savedOrder =  orderMasterDAO.save(orderMaster);
        BeanUtils.copyProperties(savedOrder, orderDTO);
        // 去库存
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster order = orderMasterDAO.findOne(orderId);
        if(order==null){
            throw new SellException(ResultMapping.ORDER_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        List<OrderDetail> orderDetails = orderDetailDAO.findByOrderId(orderId);
        if(orderDetails.isEmpty()){
            throw new SellException(ResultMapping.ORDER_DETAIL_NOT_EXIST);
        }
        orderDTO.setOrderDetails(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String consumerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDAO.findByConsumerOpenid(consumerOpenId, pageable);
        List<OrderDTO> orderDTOList = Converter.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {

        // 判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusMapping.NEW.getCode())) {
            throw new SellException(ResultMapping.ORDER_UNAVAILABLE);
        }

        // 修改订单状态
        OrderMaster order = orderMasterDAO.findOne(orderDTO.getOrderId());
        order.setOrderStatus(OrderStatusMapping.CANCELED.getCode());
        OrderMaster updateResult = orderMasterDAO.save(order);
        if(updateResult==null){
            throw new SellException(ResultMapping.ORDER_CANCEL_FAILED);
        }

        // 修改库存
        List<CartDTO> cartDTOS = new LinkedList<>();
        for (OrderDetail orderDetail: orderDTO.getOrderDetails()){
            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            cartDTOS.add(cartDTO);
        }
        productInfoService.increaseStock(cartDTOS);

        // 如果已支付 退款
        if(orderDTO.getPayStatus().equals(PayStatusMapping.SUCCESS.getCode())){
            // TODO 处理退款
        }
        orderDTO.setOrderStatus(OrderStatusMapping.CANCELED.getCode());
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        // 判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusMapping.NEW.getCode())){
            throw new SellException(ResultMapping.ORDER_UNAVAILABLE);
        }
        // 修改订单状态
        orderDTO.setOrderStatus(OrderStatusMapping.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster save = orderMasterDAO.save(orderMaster);
        if(save==null){
            throw new SellException(ResultMapping.ORDER_STATUS_UPDATE_FAILED);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO pay(OrderDTO orderDTO) {
        // 判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusMapping.NEW.getCode())){
            throw new SellException(ResultMapping.ORDER_UNAVAILABLE);
        }
        // 判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusMapping.PENDING.getCode())){
            throw new SellException(ResultMapping.PAY_STATUS_NOT_CORRECT);
        }

        // 修改支付状态
        orderDTO.setPayStatus(PayStatusMapping.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster save = orderMasterDAO.save(orderMaster);
        if(save==null){
            throw new SellException(ResultMapping.ORDER_STATUS_UPDATE_FAILED);
        }
        return orderDTO;
    }


}
