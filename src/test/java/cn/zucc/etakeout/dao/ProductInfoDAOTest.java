package cn.zucc.etakeout.dao;

import cn.zucc.etakeout.bean.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDAOTest {
    @Autowired
    private ProductInfoDAO productInfoDAO;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();

        productInfo.setProductId(12);

        productInfo.setProductName("西八");
        productInfo.setProductPrice(new BigDecimal(2.3));
        productInfo.setProductStock(10);
        productInfo.setProductDescription("description");
        productInfo.setProductIcon("http://axx");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);
        productInfoDAO.save(productInfo);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> byProductStatus = productInfoDAO.findByProductStatus(0);
        for (ProductInfo productInfo:  byProductStatus) {
            System.out.println(productInfo);
        }
    }

    @Test
    public void getStatic() {

    }
//    public static void main (String[] agrs){
//        ProductInfoServiceImpl productInfoService = new ProductInfoServiceImpl() ;
//        System.out.println("2144");
//        List<ProductInfo> productInfo=productInfoService.findUpAll();
//        System.out.println(productInfo.size());
//    }

}