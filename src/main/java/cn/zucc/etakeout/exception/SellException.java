package cn.zucc.etakeout.exception;

import cn.zucc.etakeout.mappings.ResultMapping;

/**
 * @Date ：Created in 2019/4/30 21:40
 * @Description：销售异常
 * @Created By：bing
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultMapping resultMapping){
        super(resultMapping.getMessage());
        this.code = resultMapping.getCode();
    }

    public SellException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
