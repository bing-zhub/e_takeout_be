package cn.zucc.etakeout.mappings;

import lombok.Getter;

@Getter
public enum ResultMapping {

    PRODUCT_NOT_EXIST(10, "商品不存在"), FAILED(1, "失败");

    private Integer code;

    private String message;

    ResultMapping(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
