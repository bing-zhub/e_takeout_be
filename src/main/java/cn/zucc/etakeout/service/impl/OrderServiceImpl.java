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
import cn.zucc.etakeout.util.ValueUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        orderMasterDAO.save(orderMaster);

        // 去库存
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String consumerOpenId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
