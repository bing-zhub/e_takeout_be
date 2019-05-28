package cn.zucc.etakeout.mappings;

import lombok.Getter;

@Getter
public enum ResultMapping {

    ORDER_PARAM_ERROR(-1),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    OUT_OF_STOCK(11, "库存不足"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_UNAVAILABLE(14, "订单已完结或已取消"),
    ORDER_CANCEL_FAILED(15, "订单取消失败"),
    ORDER_CANNOT_FINISH(16, "订单不可完结"),
    ORDER_STATUS_UPDATE_FAILED(17, "订单状态更新失败"),
    PAY_STATUS_NOT_CORRECT(18, "支付状态不正确"),
    CART_IS_EMPTY(19, "购物车为空"),
    PERMISSION_DENIED(20, "无此权限"),
    AMOUNT_NOT_MATCH(21, "订单金额不一致"),
    FAILED(1, "失败"),
    REGIST_FAILED(21,"用户已存在"),
    NO_USER(22,"用户不存在"),
    UNCOREECT(23,"密码错误"),
    CATRGORY_NAME_EXIT(25,"类别名称已经存在"),
    CATRGORY_NAME_NO(26,"类别名称不存在"),
    CATRGORY_TYPE_EXIT(24,"类别编号已经存在"),
    CATRGORY_TYPE_NO(27,"类别编号不存在"),
    CATEGORY_PRODUCT_EXIT(28,"该类别还有商品存在"),
    TOKEN_NOT_FOUND(29, "权限检查出错, 请重试");
    private Integer code;

    private String message;

    ResultMapping(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    ResultMapping(Integer code) {
        this.code = code;
    }
}
