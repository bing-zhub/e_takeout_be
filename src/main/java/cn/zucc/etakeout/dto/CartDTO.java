package cn.zucc.etakeout.dto;

/**
 * @Date ：Created in 2019/4/30 22:38
 * @Description：购物车DTO
 * @Created By：bing
 */
public class CartDTO {
    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
