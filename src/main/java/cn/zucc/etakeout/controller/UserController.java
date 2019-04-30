package cn.zucc.etakeout.controller;

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
        System.out.println(loginForm.getUsername() + " " + loginForm.getPassword());
        return ResultUtil.success("admin-token");
    }

    @RequestMapping("/info")
    public RootData info(){
        JsonObject jsonObject = new JsonObject();
        JsonArray roles = new JsonArray();
        roles.add("admin");
        jsonObject.add("roles",roles);
        jsonObject.addProperty("introduction", "I am a super administrator" );
        jsonObject.addProperty("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        jsonObject.addProperty("name", "Super Admin");
        return ResultUtil.success(jsonObject);
    }

    @RequestMapping("/logout")
    public RootData logout(){
        return ResultUtil.success("success");
    }
}
