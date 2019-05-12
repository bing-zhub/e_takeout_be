package cn.zucc.etakeout.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import java.math.BigDecimal;
@Data
public class ProductForm {

    @JsonProperty("id")
    private Integer productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private String productDescription;
    @JsonProperty("oldPrice")
    private BigDecimal productOldPrice;
    @JsonProperty("info")
    private String productInfo;
    @JsonProperty("icon")
    private String productIcon;
    @JsonProperty("image")
    private String productImages;
    @JsonProperty("status")
    private Integer productStatus;
    @JsonProperty("type")
    private Integer categoryType;
}
