package cn.zucc.etakeout.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoData {
    @JsonProperty("id")
    private Integer productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("oldPrice")
    private BigDecimal productOldPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

    @JsonProperty("image")
    private String productImages;

    @JsonProperty("sellCount")
    private Integer productSellCount;

    @JsonProperty("rating")
    private Integer productRating;
}
