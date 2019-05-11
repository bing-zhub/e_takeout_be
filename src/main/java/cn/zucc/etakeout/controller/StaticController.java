package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.bean.OrderDetail;
import cn.zucc.etakeout.bean.OrderMaster;
import cn.zucc.etakeout.bean.ProductInfo;
import cn.zucc.etakeout.dao.OrderMasterDAO;
import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.service.OrderService;
import cn.zucc.etakeout.service.ProductInfoService;
import cn.zucc.etakeout.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/static")
public class StaticController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderService orderService;
    @RequestMapping("/count")
    public RootData countProduct(){
        int dayTime=24*60*60*1000;
        List<Integer> weekCount=Arrays.asList(0,0,0,0,0,0,0);
        Date now=new Date();
        Long diff;
        List<ProductInfo> productInfoList=productInfoService.count();
        Date createDate;
        for (ProductInfo productInfo:productInfoList){
            createDate=productInfo.getCreatTime();
            diff=now.getTime()-createDate.getTime();
            int day= (int) (diff/dayTime);
            if (day<7)
                for(int i=0;i<=day;i++){
                    weekCount.set(i, weekCount.get(i)+1);}
            else
                for(int i=0;i<=6;i++){
                weekCount.set(6, weekCount.get(day)+1);}
        }


        for (Integer i:weekCount){
            System.out.println(i);
        }
        return ResultUtil.success(weekCount);


    }
    @RequestMapping("/consumption")
    public RootData perConsumption() {
        int dayTime = 24 * 60 * 60 * 1000;
        List<Double> weekConsumption = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        List<Integer> weekDeal = Arrays.asList(0, 0, 0, 0, 0, 0, 0);

        float perConsumption;
        Date now = new Date();
        Long diff;
        List<OrderMaster> orderMasterList = orderService.findAll();

        Date createDate;
        for (OrderMaster orderMaster : orderMasterList) {
            List<OrderDetail> orderDetailList = orderService.findDetail(orderMaster.getOrderId());
            perConsumption = 0;
            for (OrderDetail orderDetail : orderDetailList) {
                perConsumption += orderDetail.getProductQuantity() * orderDetail.getProductPrice().doubleValue();
            }
            createDate = orderMaster.getCreateTime();
            diff = now.getTime() - createDate.getTime();
            int day = (int) (diff / dayTime);
            if (day < 7){
//                for (int i = 0; i <= day; i++) {
                    weekConsumption.set(day, weekConsumption.get(day) + perConsumption);
                    weekDeal.set(day, weekDeal.get(day) + 1);
                }
//            else{
////                for (int i = 0; i <= 6; i++) {
//                    weekConsumption.set(day, weekConsumption.get(day) + perConsumption);
//                    weekDeal.set(day, weekDeal.get(day) + 1);
//                }

            for (int i = 0; i <= 6; i++) {
                weekConsumption.set(i, weekConsumption.get(i) / weekDeal.get(i));
            }

        }
        return  ResultUtil.success(weekConsumption);
    }
    @RequestMapping("/complete")
    public  RootData countComplete() {
        int dayTime = 24 * 60 * 60 * 1000;
        List<Integer> weekCompleted= Arrays.asList(0, 0, 0, 0, 0, 0, 0);
        List<Integer> weekUnCompleted= Arrays.asList(0, 0, 0, 0, 0, 0, 0);
        int completed = 0, uncompleted = 0;
        List<OrderMaster> orderMasterList = orderService.findAll();
        Date now = new Date();
        Long diff;
        Date createDate;
        for (OrderMaster orderMaster : orderMasterList) {
            createDate = orderMaster.getCreateTime();
            diff = now.getTime() - createDate.getTime();
            int day = (int) (diff / dayTime);
            if (day < 7) {
                if (orderMaster.getOrderStatus()==1)
//                    for (int i = 0; i <= day; i++) {
                        weekCompleted.set(day, weekCompleted.get(day) + 1);
//                    }
//                else if (orderMaster.getOrderStatus()==2){
////                    for (int i = 0; i <= day; i++) {
//                        weekUnCompleted.set(day, weekUnCompleted.get(day) + 1);
////                    }
//                }
            }
            else {
                if (orderMaster.getOrderStatus()==1)
//                    for (int i = 0; i <= 6; i++) {
                        weekCompleted.set(day, weekCompleted.get(day) + 1);
//                    }
//                else if (orderMaster.getOrderStatus()==2){
////                    for (int i = 0; i <= 6; i++) {
//                        weekUnCompleted.set(day, weekUnCompleted.get(day) + 1);
////                    }
//                }
            }
        }

        List<Integer> result = new ArrayList<>();
        result.add(completed);
        result.add(uncompleted);
        return ResultUtil.success(result);

    }
    @RequestMapping("/income")
    public  RootData income(){
            int dayTime = 24 * 60 * 60 * 1000;
            List<Double> weekIncome = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

            float perConsumption;
            Date now = new Date();
            Long diff;
            List<OrderMaster> orderMasterList = orderService.findAll();

            Date createDate;
            for (OrderMaster orderMaster : orderMasterList) {
                List<OrderDetail> orderDetailList = orderService.findDetail(orderMaster.getOrderId());
                perConsumption = 0;
                for (OrderDetail orderDetail : orderDetailList) {
                    perConsumption += orderDetail.getProductQuantity() * orderDetail.getProductPrice().doubleValue();
                }
                createDate = orderMaster.getCreateTime();
                diff = now.getTime() - createDate.getTime();
                int day = (int) (diff / dayTime);
                if (day < 7){
                    weekIncome.set(day, weekIncome.get(day) + perConsumption);
                }




            }
            return  ResultUtil.success(weekIncome);
    }
}
