package cn.zucc.etakeout.dto;

import cn.zucc.etakeout.bean.OrderDetail;
import cn.zucc.etakeout.mappings.OrderStatusMapping;
import cn.zucc.etakeout.mappings.PayStatusMapping;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Date ：Created in 2019/4/30 21:31
 * @Description：DataTransferObject
 * @Created By：bing
 */
@Data
public class OrderDTO {
    private String orderId;

    private String consumerName;

    private String consumerPhone;

    private String consumerAddress;

    private String consumerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatusMapping.NEW.getCode();

    private Integer payStatus = PayStatusMapping.PENDING.getCode();

    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetails;
}
