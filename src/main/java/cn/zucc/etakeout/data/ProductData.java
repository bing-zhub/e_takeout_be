package cn.zucc.etakeout.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Date ：Created in 2019/4/29 23:44
 * @Description：返回前端数据, 由于数据保护不可将数据库表对前端(尤其是买家端)完全可见.
 * @Created By：bing
 */

@Data
public class ProductData {

    // 返回前端的时候是name字段
    @JsonProperty("name")
    // 本地使用字段
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoData> productInfoList;
}
