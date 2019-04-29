package cn.zucc.etakeout.mappings;

import lombok.Getter;

@Getter
public enum  CommonMapping {

    SUCCESS(0, "成功"), FAILED(1, "失败");

    private Integer code;

    private String message;

    CommonMapping(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
