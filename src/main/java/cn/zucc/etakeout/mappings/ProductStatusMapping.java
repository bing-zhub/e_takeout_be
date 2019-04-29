package cn.zucc.etakeout.mappings;

import lombok.Getter;

@Getter
public enum ProductStatusMapping {

    ONSALE(0, "正在出售"),SOLDOUT(1, "下架");

    private Integer code;

    private String message;

    ProductStatusMapping(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
