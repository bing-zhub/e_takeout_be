package cn.zucc.etakeout.bean;

import cn.zucc.etakeout.mappings.OrderStatusMapping;
import cn.zucc.etakeout.mappings.PayStatusMapping;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Date ：Created in 2019/4/30 20:46
 * @Description：订单主表
 * @Created By：bing
 */
@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class OrderMaster {

    @Id
    private String orderId;

    private String sellerId;

    private String consumerName;

    private String consumerPhone;

    private String consumerAddress;

    private String consumerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatusMapping.NEW.getCode();

    private Integer payStatus = PayStatusMapping.PENDING.getCode();

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

}
