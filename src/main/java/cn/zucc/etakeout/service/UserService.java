package cn.zucc.etakeout.service;

import cn.zucc.etakeout.bean.UserInfo;

public interface UserService {
    //用户注册
    UserInfo register(UserInfo userInfo);
    //添加用户
    UserInfo find(String username);
}
