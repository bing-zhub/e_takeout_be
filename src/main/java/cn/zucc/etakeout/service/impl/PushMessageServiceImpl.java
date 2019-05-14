package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.service.PushMessageService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        templateMessage.setTemplateId("");
        templateMessage.setToUser(orderDTO.getConsumerOpenid());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "请及时收货"),
                new WxMpTemplateData("keyword1", "微信"),
                new WxMpTemplateData("keyword2", orderDTO.getConsumerPhone()),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatus() + ""),
                new WxMpTemplateData("keyword5", orderDTO.getOrderAmount() + "元"),
                new WxMpTemplateData("remark", "Thank you")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
