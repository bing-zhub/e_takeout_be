package cn.zucc.etakeout.dao;

import cn.zucc.etakeout.bean.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductInfoDAO extends JpaRepository<ProductInfo, Integer> {
    List<ProductInfo> findByProductStatus(Integer Status);
    List<ProductInfo> findByCategoryType(Integer categoryType);

    String productAddedQuery = "SELECT WEEKDAY(create_time) as day, COUNT(product_id) as value " +
            "FROM product_info  WHERE create_time >= date_format(date_sub(date_sub(now(), INTERVAL WEEKDAY(NOW()) DAY), INTERVAL 1 WEEK), '%Y-%m-%d')" +
            "GROUP BY WEEKDAY(create_time)";
    String incomeInOneWeekQuery = "SELECT WEEKDAY(create_time) as day, SUM(order_amount) as value " +
            "FROM order_master WHERE create_time >= date_format(date_sub(date_sub(now(), INTERVAL WEEKDAY(NOW()) DAY), INTERVAL 1 WEEK), '%Y-%m-%d') " +
            "GROUP BY WEEKDAY(create_time)";
    String averageInOneWeekQuery = "SELECT WEEKDAY(create_time) as day, SUM(order_amount)/COUNT(order_id) as value FROM order_master " +
            "WHERE create_time >= date_format(date_sub(date_sub(now(), INTERVAL WEEKDAY(NOW()) DAY), INTERVAL 1 WEEK), '%Y-%m-%d') " +
            "GROUP BY WEEKDAY(create_time)";
    String orderClosedQuery = "SELECT WEEKDAY(create_time) as day, COUNT(order_id) as value " +
            "FROM order_master WHERE create_time >= date_format(date_sub(date_sub(now(), INTERVAL WEEKDAY(NOW()) DAY), INTERVAL 1 WEEK), '%Y-%m-%d') and order_status = 1 " +
            "GROUP BY WEEKDAY(create_time)";

    @Query(value = productAddedQuery, nativeQuery = true)
    List<Object[]> getCountOfProductAddedInOneWeek();

    @Query(value = incomeInOneWeekQuery, nativeQuery = true)
    List<Object[]> getIncomeInOneWeek();

    @Query(value = averageInOneWeekQuery, nativeQuery = true)
    List<Object[]> getAverage();

    @Query(value = orderClosedQuery, nativeQuery = true)
    List<Object[]> getOrderClosed();
}
