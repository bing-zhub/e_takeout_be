package cn.zucc.etakeout.mappings;

import lombok.Getter;

@Getter
public enum OrderStatusMapping {
    NEW(0,"新订单"), FINISHED(1,"完结"), CANCELED(2,"已取消");
    OrderStatusMapping(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    private int code;
    private String message;
}
