package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.dto.OrderDTO;
import cn.zucc.etakeout.exception.SellException;
import cn.zucc.etakeout.form.OrderCreateForm;
import cn.zucc.etakeout.form.OrderDetailQueryForm;
import cn.zucc.etakeout.form.OrderQueryForm;
import cn.zucc.etakeout.mappings.ResultMapping;
import cn.zucc.etakeout.service.OrderService;
import cn.zucc.etakeout.util.Converter;
import cn.zucc.etakeout.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date ：Created in 2019/5/2 19:31
 * @Description：处理买家订单接口
 * @Created By：bing
 */
@RestController
@RequestMapping("/consumer/order")
public class  ConsumerOrderController {

    @Autowired
    OrderService orderService;

    // 创建订单
    @PostMapping("/create")
    // @Valid会对form中的注解进行检查 结果放入BindingResult
    public RootData<Map<String, String>> create(@RequestBody @Valid OrderCreateForm orderForm,
                                                BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new SellException(ResultMapping.ORDER_PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = Converter.convert(orderForm);
        if(orderDTO.getOrderDetails().isEmpty()){
            throw new SellException(ResultMapping.CART_IS_EMPTY);
        }
        OrderDTO createdOrder = orderService.create(orderDTO);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("orderId", createdOrder.getOrderId());
        return ResultUtil.success(map);
    }

    // 订单列表
    @PostMapping("/list")
    public RootData<List<OrderDTO>> list(@RequestBody @Valid OrderQueryForm queryForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new SellException(ResultMapping.ORDER_PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        PageRequest pageRequest = new PageRequest(queryForm.getPage(), queryForm.getSize(), Sort.Direction.DESC, "createTime");
        Page<OrderDTO> orderDTOS = orderService.findList(queryForm.getOpenId(), pageRequest);

        return ResultUtil.success(orderDTOS.getContent());
    }

    // 订单详情
    @PostMapping("/detail")
    public RootData<OrderDTO> detail(@RequestBody @Valid OrderDetailQueryForm queryForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new SellException(ResultMapping.ORDER_PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO =  orderService.findOne(queryForm.getOrderId());
        if(!queryForm.getOpenId().equals(orderDTO.getConsumerOpenid())){
            throw new SellException(ResultMapping.PERMISSION_DENIED);
        }
        return ResultUtil.success(orderDTO);
    }

    // 取消订单
    @PostMapping("/cancel")
    public RootData cancel(@RequestBody @Valid OrderDetailQueryForm queryForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new SellException(ResultMapping.ORDER_PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = orderService.findOne(queryForm.getOrderId());
        if(!queryForm.getOpenId().equals(orderDTO.getConsumerOpenid())){
            throw new SellException(ResultMapping.PERMISSION_DENIED);
        }
        return ResultUtil.success(orderService.cancel(orderDTO));
    }

    // 完结订单
    @GetMapping("/finish")
    public RootData finish(@RequestBody @Valid OrderDetailQueryForm queryForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new SellException(ResultMapping.ORDER_PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = orderService.findOne(queryForm.getOrderId());
        if(!queryForm.getOpenId().equals(orderDTO.getConsumerOpenid())){
            throw new SellException(ResultMapping.PERMISSION_DENIED);
        }
        return ResultUtil.success(orderService.finish(orderDTO));
    }
}
