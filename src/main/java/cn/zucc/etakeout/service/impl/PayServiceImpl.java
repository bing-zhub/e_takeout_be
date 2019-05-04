package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.service.PayService;
import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
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

    @Override
    public void create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getConsumerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName("微信H5测试");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        System.out.println(payRequest);
        PayResponse pay = bestPayService.pay((payRequest));
        System.out.println(pay);
    }
}
