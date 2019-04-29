package cn.zucc.etakeout.util;

import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.mappings.CommonMapping;

public class ResultUtil {
    public static RootData success(Object data){
        RootData rootData = new RootData();
        rootData.setCode(CommonMapping.SUCCESS.getCode());
        rootData.setMessage(CommonMapping.SUCCESS.getMessage());
        rootData.setData(data);
        return rootData;
    }
}
