package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.bean.UserInfo;
import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.exception.SellException;
import cn.zucc.etakeout.form.LoginForm;
import cn.zucc.etakeout.mappings.ResultMapping;
import cn.zucc.etakeout.service.UserService;
import cn.zucc.etakeout.util.ResultUtil;
import cn.zucc.etakeout.util.ValueUtil;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    @PostMapping("/list")
    public RootData listSeller(){
        return ResultUtil.success(userService.listSellers());
    }

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
                                request.getRemoteAddr() + " " + userInfo.getRole() + " " + userInfo.getUsername(),
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

    @PostMapping("/info")
    public RootData info(@RequestBody Map<String, String> map){
        Map ret = new HashMap();
        String token = map.get("token");
        String info = stringRedisTemplate.opsForValue().get(token);
        String role = info.split(" ")[1];
        String username = info.split(" ")[2];
        UserInfo userInfo = userService.find(username);
        List<String> roles = new LinkedList<>();
        roles.add(role);
        ret.put("avatar", userInfo.getAvatar());
        ret.put("introduction", userInfo.getIntroduction());
        ret.put("roles", roles);
        ret.put("username", userInfo.getUsername());
        return ResultUtil.success(ret);
    }

    @GetMapping("/logout")
    public RootData logout(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        stringRedisTemplate.opsForValue().getOperations().delete(token);
        return ResultUtil.success("success");
    }

    @PostMapping("/active")
    public RootData activateUser(@RequestBody Map<String, String> map){
        return ResultUtil.success(userService.active(map.get("username")));
    }

    @PostMapping("/deactive")
    public RootData deactiveUser(@RequestBody Map<String, String> map){
        return ResultUtil.success(userService.deactive(map.get("username")));
    }
}
