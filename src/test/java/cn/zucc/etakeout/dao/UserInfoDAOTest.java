package cn.zucc.etakeout.dao;

import cn.zucc.etakeout.bean.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void login(){
        UserInfo admin = userDAO.findOne("admin");
        System.out.println(admin.getPwd());
    }
}