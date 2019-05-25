package cn.zucc.etakeout.exception;

import cn.zucc.etakeout.mappings.ResultMapping;
import lombok.Data;

/**
 * @Date ：Created in 2019/4/30 21:40
 * @Description：销售异常
 * @Created By：bing
 */
@Data
public class SellException extends RuntimeException{

    private Integer code;
    private String message;

    public SellException(ResultMapping resultMapping){
//        super(resultMapping.getMessage());

        System.out.println("[ERROR]" + resultMapping.getMessage());
        this.code = resultMapping.getCode();
        this.message = resultMapping.getMessage();
    }

    public SellException(Integer code, String message){
//        super(message);
        System.out.println("[ERROR]" + message);
        this.code = code;
        this.message = message;
    }
}
