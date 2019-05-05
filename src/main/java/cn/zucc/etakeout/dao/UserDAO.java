package cn.zucc.etakeout.dao;

import cn.zucc.etakeout.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<UserInfo, String> {
}
