package cn.zucc.etakeout.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Date ：Created in 2019/4/29 23:43
 * @Description：JavaBean 映射数据库表
 * @Created By：bing
 */

@Entity
@Data
public class ProductInfo {
    @Id
    private int productId;

    private String productName;

    private BigDecimal productPrice;

    private String productDescription;

    private Integer productSellCount;

    private BigDecimal productOldPrice;

    private Integer productRating;

    private String productIcon;

    private String productImages;

    private Integer productStock;

    private Integer productStatus;

    private Integer categoryType;

    private BigDecimal score;

}
