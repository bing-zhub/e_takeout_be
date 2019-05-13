package cn.zucc.etakeout.mappings;

import lombok.Getter;

/**
 * @Date ：Created in 2019/5/13 17:44
 * @Description：统计类别对应
 * @Created By：bing
 */
@Getter
public enum StatisMapping {

    ProductAdded(0, "一周内添加的商品数"),
    Income(1, "一周内收入"),
    AverageConsumption(2, "一周内人均消费"),
    OrderClosed(3, "一周内完结订单数");

    private String message;
    private Integer code;

    StatisMapping(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
