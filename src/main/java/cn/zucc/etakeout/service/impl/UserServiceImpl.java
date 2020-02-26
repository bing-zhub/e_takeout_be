package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.bean.UserInfo;
import cn.zucc.etakeout.dao.UserDAO;
import cn.zucc.etakeout.exception.SellException;
import cn.zucc.etakeout.mappings.ResultMapping;
import cn.zucc.etakeout.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserInfo register(UserInfo userInfo) {
        UserInfo one=userDAO.findOne(userInfo.getUsername());
        if(one==null){
            userDAO.save(userInfo);
        }
        else{
            throw new SellException(ResultMapping.REGIST_FAILED);
        }

        return userInfo;
    }

    @Override
    public UserInfo find(String username) {
        return userDAO.findOne(username);
    }

    @Override
    public List<UserInfo> listSellers() {
        return userDAO.findAllByRole("seller");
    }

    @Override
    public UserInfo active(String username) {
        UserInfo one = userDAO.findOne(username);
        one.setIntroduction("刚刚被捞回来");
        return userDAO.save(one);
    }

    @Override
    public UserInfo deactive(String username) {
        UserInfo one = userDAO.findOne(username);
        one.setIntroduction("disabled");
        return userDAO.save(one);
    }
}
