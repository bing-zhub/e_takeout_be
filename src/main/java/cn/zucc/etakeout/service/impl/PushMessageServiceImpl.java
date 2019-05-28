package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.bean.OrderDetail;
import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.service.PushMessageService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Date ：Created in 2019/5/14 16:36
 * @Description：订单消息变更推送
 * @Created By：bing
 */
@Service
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Override
    public void orderStatusUpdate(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId("PXtM4vjMxhC11WYxZNc8Io2mWI4fykAiOlPCFu74sGE");
        templateMessage.setToUser(orderDTO.getConsumerOpenid());
        List<OrderDetail> orderDetails = orderDTO.getOrderDetails();
        String productName = orderDetails.get(0).getProductName();
        Date dateNow =  new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        if(orderDetails.size()>1) productName+="等";
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "您已购买成功"),
                new WxMpTemplateData("keyword1", productName),
                new WxMpTemplateData("keyword2", orderDTO.getOrderAmount()+"元"),
                new WxMpTemplateData("keyword3", orderDetails.size()+""),
                new WxMpTemplateData("keyword4", sdf.format(dateNow)),
                new WxMpTemplateData("remark", "很高兴为您提供服务!")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
