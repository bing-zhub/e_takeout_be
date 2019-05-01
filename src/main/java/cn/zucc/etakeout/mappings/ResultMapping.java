package cn.zucc.etakeout.mappings;

import lombok.Getter;

@Getter
public enum ResultMapping {

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    OUT_OF_STOCK(11, "库存不足"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_CANNOT_CANCEL(14, "订单已完结或已取消"),
    ORDER_CANCEL_FAILED(15, "订单取消失败"),
    FAILED(1, "失败");

    private Integer code;

    private String message;

    ResultMapping(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
