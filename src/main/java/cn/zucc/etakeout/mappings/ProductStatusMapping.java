package cn.zucc.etakeout.mappings;

import lombok.Getter;

/**
 * @Date ：Created in 2019/4/29 23:41
 * @Description：产品状态码与名称映射, 便于维护
 * @Created By：bing
 */

// lombok注解 会自动生成get方法
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
