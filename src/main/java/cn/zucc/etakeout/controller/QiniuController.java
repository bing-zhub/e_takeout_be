package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.config.QiniuClientConfig;
import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.util.ResultUtil;
import com.qiniu.storage.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.util.HashMap;

/**
 * @Date ：Created in 2019/5/5 14:20
 * @Description：七牛上传token获取
 * @Created By：bing
 */
@RestController
@RequestMapping("/qiniu")
public class QiniuController {

    @Autowired
    private QiniuClientConfig qiniuClientConfig;

    @GetMapping("/upload/token")
    public RootData getUploadToken(HashMap<String, String> map){
        Auth auth = Auth.create(qiniuClientConfig.getAk(), qiniuClientConfig.getSk());
        String token = auth.uploadToken(qiniuClientConfig.getBulkName());
        map.put("qiniu_token", token);
        map.put("qiniu_key", qiniuClientConfig.getAk());
        return ResultUtil.success(map);
    }
}
