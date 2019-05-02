package cn.zucc.etakeout.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Date ：Created in 2019/5/2 20:45
 * @Description：订单查询表单
 * @Created By：bing
 */
@Data
public class OrderQueryForm {

    @NotEmpty(message = "openId必填")
    private String openId;

    private Integer page = 0;

    private Integer size = 10;
}
