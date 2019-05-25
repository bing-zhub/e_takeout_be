package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.exception.SellException;
import cn.zucc.etakeout.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Date ：Created in 2019/5/25 16:32
 * @Description：
 * @Created By：bing
 */
@ControllerAdvice
public class ErrorController {

    @ResponseBody
    @ExceptionHandler(value = SellException.class)
    public RootData exceptionHandler(SellException ex){
        return ResultUtil.error(ex);
    }
}
