package cn.zucc.etakeout.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Date ：Created in 2019/4/30 16:54
 * @Description：从前端接收登录信息
 * @Created By：bing
 */
@Data
public class LoginForm {
    @NotEmpty(message = "登录名不能为空")
    private String username;

    @NotEmpty(message =  "密码不能为空")
    private String password;

}
