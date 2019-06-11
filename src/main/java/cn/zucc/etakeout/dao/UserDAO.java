package cn.zucc.etakeout.dao;

import cn.zucc.etakeout.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<UserInfo, String> {
    List<UserInfo> findAllByRole(String role);
}
