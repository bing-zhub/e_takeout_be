package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.mappings.StatisMapping;
import cn.zucc.etakeout.service.ProductInfoService;
import cn.zucc.etakeout.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/static")
public class StaticController {

    @Autowired
    private ProductInfoService productInfoService;

    //查看商品数目
    @RequestMapping("/week/count")
    public RootData countProduct() {
        return ResultUtil.success(productInfoService.getWeekStatics(StatisMapping.ProductAdded.getCode()));
    }

    // 人均消费
    @RequestMapping("/week/consumption")
    public RootData perConsumption() {
        return ResultUtil.success(productInfoService.getWeekStatics(StatisMapping.AverageConsumption.getCode()));
    }

    // 完结订单数
    @RequestMapping("/week/complete")
    public RootData countComplete() {
        return ResultUtil.success(productInfoService.getWeekStatics(StatisMapping.OrderClosed.getCode()));
    }

    // 总收入
    @RequestMapping("/week/income")
    public RootData income() {
        return ResultUtil.success(productInfoService.getWeekStatics(StatisMapping.Income.getCode()));
    }

    @RequestMapping("/total")
    public RootData totalOrder() {
        return ResultUtil.success(productInfoService.getTotalStatics());
    }

}
