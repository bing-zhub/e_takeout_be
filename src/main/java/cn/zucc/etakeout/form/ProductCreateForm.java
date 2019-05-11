package cn.zucc.etakeout.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
@Data
public class ProductCreateForm {
    @NotEmpty(message = "商品名称必填")
    @JsonProperty("name")
    private String productName;

    @NotEmpty(message = "商品价格必填")
    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("oldPrice")
    private BigDecimal productOldPrice;

    @JsonProperty("info")
    private String productInfo  = "这个卖家很懒, 还没上传~" ;

    @JsonProperty("description")
    private String productDescription = "这个卖家很懒, 还没上传~";

    @NotEmpty(message = "图片必须上传")
    @JsonProperty("icon")
    private String productIcon = "http://pr0o6uaio.bkt.clouddn.com/FrWYf-tRZvlKCZJB8SXj4SoMNH94";

    @JsonProperty("image")
    private String productImages = "http://pr0o6uaio.bkt.clouddn.com/FrWYf-tRZvlKCZJB8SXj4SoMNH94";

    @NotEmpty(message = "商品类别必选")
    @JsonProperty("type")
    private Integer categoryType;

    private Integer productStatus = 0;

    @NotEmpty(message = "商品库存必填")
    @JsonProperty("stock")
    private Integer productStock;
}
