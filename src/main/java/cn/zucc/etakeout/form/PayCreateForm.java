package cn.zucc.etakeout.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Date ：Created in 2019/5/4 11:25
 * @Description：订单创建表单
 * @Created By：bing
 */
@Data
public class PayCreateForm {
    @NotEmpty(message = "orderId必填")
    private String orderId;

    @NotEmpty(message = "returnUrl必填")
    private String returnUrl;
}
