package cn.zucc.etakeout.bean;

import cn.zucc.etakeout.mappings.OrderStatusMapping;
import cn.zucc.etakeout.mappings.PayStatusMapping;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Date ：Created in 2019/4/30 20:46
 * @Description：订单主表
 * @Created By：bing
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
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

}
