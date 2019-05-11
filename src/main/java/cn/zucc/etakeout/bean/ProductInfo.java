package cn.zucc.etakeout.bean;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Date ：Created in 2019/4/29 23:43
 * @Description：JavaBean 映射数据库表
 * @Created By：bing
 */

@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class ProductInfo {
    @Id
    @GeneratedValue
    private Integer productId;

    private String productName;

    private BigDecimal productPrice;

    private String productDescription = "这个卖家很懒, 还没上传~";

    private Integer productSellCount = 0;

    private BigDecimal productOldPrice;

    private Integer productRating = 100;

    private String productInfo = "这个卖家很懒, 还没上传~";

    private String productIcon = "http://pr0o6uaio.bkt.clouddn.com/FrWYf-tRZvlKCZJB8SXj4SoMNH94";

    private String productImages = "http://pr0o6uaio.bkt.clouddn.com/FrWYf-tRZvlKCZJB8SXj4SoMNH94";

    private Integer productStock = 0;

    private Integer productStatus = 0;

    private Integer categoryType;

    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;

    private BigDecimal score = new BigDecimal(5);


}
