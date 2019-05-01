package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.bean.User;
import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.form.LoginForm;
import cn.zucc.etakeout.util.ResultUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Date ：Created in 2019/4/30 16:33
 * @Description：user login and other operation
 * @Created By：bing
 */
@RestController
@RequestMapping("/user")
public class UserController {

    // 接收json数据
    @RequestMapping("/login")
    public RootData login(@RequestBody LoginForm loginForm){
        return ResultUtil.success("admin-token");
    }

    @RequestMapping("/info")
    public RootData info(){
        User user = new User();
        user.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        user.setIntroduction("I am a super administrator");
        user.setName("Super Admin");
        user.setRole("[admin]");
        return ResultUtil.success(user);
    }

    @RequestMapping("/logout")
    public RootData logout(){
        return ResultUtil.success("success");
    }
}
