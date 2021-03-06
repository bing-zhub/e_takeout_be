package cn.zucc.etakeout.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Date ：Created in 2019/5/1 22:54
 * @Description：user for admin pannel
 * @Created By：bing
 */
@Entity
@Data
public class UserInfo {

    @Id
    String username;
    String pwd;
    String role;
    String introduction;
    String avatar;
    String openId;
}
