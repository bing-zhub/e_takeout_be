package cn.zucc.etakeout.data;

import lombok.Data;

/**
 * @Date ：Created in 2019/4/29 23:42
 * @Description：返回数据根结构
 * @Created By：bing
 */

@Data
public class RootData<T> {

    // 自定义状态码
    private Integer code;

    // 状态码的详细解释
    private String message;

    // 具体数据
    private T data;
}
