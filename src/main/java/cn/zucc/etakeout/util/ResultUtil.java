package cn.zucc.etakeout.util;

import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.mappings.CommonMapping;

/**
 * @Date ：Created in 2019/4/29 23:41
 * @Description：将返回根结构方法抽离出来
 * @Created By：bing
 */

public class ResultUtil {
    public static RootData success(Object data){
        RootData rootData = new RootData();
        rootData.setCode(CommonMapping.SUCCESS.getCode());
        rootData.setMessage(CommonMapping.SUCCESS.getMessage());
        rootData.setData(data);
        return rootData;
    }
}
