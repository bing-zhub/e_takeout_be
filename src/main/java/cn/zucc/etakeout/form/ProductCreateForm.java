package cn.zucc.etakeout.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
@Data
public class ProductCreateForm {
    @NotEmpty(message = "productName必填")
    private String name;
    @NotEmpty(message = "productPrice必填")
    private BigDecimal price;
    @NotEmpty(message = "productPrice必填")
    private BigDecimal oldPrice;
    @NotEmpty(message = "info必填")
    private Integer info;
    @NotEmpty(message = "description必填")
    private String description;
    @NotEmpty(message = "icon必填")
    private String icon;
    @NotEmpty(message = "productStatus必填")
    private String image;
    @NotEmpty(message = "categoryType")
    private Integer type;
}
