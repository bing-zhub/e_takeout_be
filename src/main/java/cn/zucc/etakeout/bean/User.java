package cn.zucc.etakeout.bean;

import lombok.Data;

/**
 * @Date ：Created in 2019/5/1 22:54
 * @Description：user for admin pannel
 * @Created By：bing
 */
@Data
public class User {
    String role;
    String introduction;
    String avatar;
    String name;
}
