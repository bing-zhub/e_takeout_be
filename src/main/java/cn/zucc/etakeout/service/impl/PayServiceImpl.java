package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.exception.SellException;
import cn.zucc.etakeout.mappings.ResultMapping;
import cn.zucc.etakeout.service.OrderService;
import cn.zucc.etakeout.service.PayService;
import cn.zucc.etakeout.util.ValueUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Date ：Created in 2019/5/4 11:32
 * @Description：订单支付
 * @Created By：bing
 */
@Service
public class PayServiceImpl implements PayService{

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getConsumerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName("微信H5测试");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        return bestPayService.pay(payRequest);
    }

    @Override
    public PayResponse notify(String notifyData) {
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        OrderDTO order = orderService.findOne(payResponse.getOrderId());
        if (order == null) {
            throw new SellException(ResultMapping.ORDER_NOT_EXIST);
        }

        // 金额校验
        boolean moneyMatch = ValueUtil.equals(order.getOrderAmount().doubleValue(), payResponse.getOrderAmount());
        if (!moneyMatch) {
            throw new SellException(ResultMapping.AMOUNT_NOT_MATCH);
        }
        orderService.pay(order);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        return bestPayService.refund(refundRequest);
    }
}
