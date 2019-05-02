package cn.zucc.etakeout.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Date ：Created in 2019/5/2 21:06
 * @Description：订单详情查询表单
 * @Created By：bing
 */
@Data
public class OrderDetailQueryForm {

    @NotEmpty(message = "OpenId必填")
    private String openId;

    @NotEmpty(message = "订单ID必填")
    private String orderId;
}
