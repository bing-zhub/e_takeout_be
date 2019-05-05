package cn.zucc.etakeout.service;

public interface ProductCategoryService {
    ProductCategoryService addProductCategoryService(String categoryName,Integer categoryType);
    void deleteProductCategory(Integer categoryType);
    void changeCategoryName(Integer categoryType,String categoryName);
    void changeCategoryType(Integer categoryType);
    void getProductCategory(Integer categoryType);

}
