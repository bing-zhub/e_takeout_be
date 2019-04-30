package cn.zucc.etakeout.mappings;
import lombok.Getter;

@Getter
public enum PayStatusMapping {

    PENDING(0,"等待支付"), SUCCESS(1,"支付成功");
    PayStatusMapping(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    private int code;
    private String message;


}
