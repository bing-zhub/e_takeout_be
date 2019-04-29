package cn.zucc.etakeout.mappings;

import lombok.Getter;
/**
 * @Date ：Created in 2019/4/29 23:44
 * @Description：信息返回状态码
 * @Created By：bing
 */

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
