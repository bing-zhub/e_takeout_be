package cn.zucc.etakeout.form;

import cn.zucc.etakeout.bean.OrderDetail;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @Date ：Created in 2019/5/2 19:34
 * @Description：创建订单表单
 * @Created By：bing
 */
@Data
public class OrderCreateForm {

    @NotEmpty(message = "卖家标识必填")
    private String sellerId;

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号码必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "OpenID必填")
    private String openId;

    @NotEmpty(message = "购物车不能为空")
    private List<OrderDetail> items;
}
