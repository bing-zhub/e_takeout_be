package cn.zucc.etakeout.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Id;
import java.math.BigDecimal;
@Data
public class ProductForm {
    @NotEmpty(message = "productId必填")
    private int productId;

    private String productName;

    private BigDecimal productPrice;

    private String productDescription;

    private BigDecimal productOldPrice;

    private String productInfo;

    private String productIcon;

    private String productImages;

    private Integer productStatus;

    private Integer categoryType;
}
