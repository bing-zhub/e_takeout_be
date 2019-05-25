package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.bean.UserInfo;
import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.exception.SellException;
import cn.zucc.etakeout.form.LoginForm;
import cn.zucc.etakeout.mappings.ResultMapping;
import cn.zucc.etakeout.service.UserService;
import cn.zucc.etakeout.util.ResultUtil;
import cn.zucc.etakeout.util.ValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @Date ：Created in 2019/4/30 16:33
 * @Description：user login and other operation
 * @Created By：bing
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 接收json数据
    @PostMapping("/login")
    public RootData login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        UserInfo userInfo = userService.find(loginForm.getUsername());
        if(userInfo ==null){
            throw new SellException(ResultMapping.NO_USER);
        } else {
            if (userInfo.getPwd().equals(loginForm.getPassword())){
                String token = ValueUtil.sign(loginForm.getUsername(), request.getRemoteAddr());

                stringRedisTemplate
                        .opsForValue()
                        .set(token,
                                request.getRemoteAddr(),
                                ValueUtil.EXPIRE_TIME,
                                TimeUnit.MILLISECONDS);

                return ResultUtil.success(token);
            } else {
                throw new SellException(ResultMapping.UNCOREECT);
            }
        }
    }

    @PostMapping("/register")
    public RootData register(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(loginForm.getUsername());
        userInfo.setPwd(loginForm.getPassword());
        userService.register(userInfo);
        String token = ValueUtil.sign(loginForm.getUsername(), request.getRemoteAddr());
        return ResultUtil.success(token);
    }

    @GetMapping("/info")
    public RootData info(){
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfo.setIntroduction("I am a super administrator");
        userInfo.setUsername("Super Admin");
        userInfo.setRole("[admin]");
        return ResultUtil.success(userInfo);
    }

    @GetMapping("/logout")
    public RootData logout(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        stringRedisTemplate.opsForValue().getOperations().delete(token);
        return ResultUtil.success("success");
    }
}
