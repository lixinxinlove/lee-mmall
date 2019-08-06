package com.lixinxinlove.service;

import com.github.pagehelper.PageInfo;
import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.pojo.Product;
import com.lixinxinlove.vo.ProductDetailVo;
import org.springframework.stereotype.Service;

public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse getList(int pageNum, int pageSize);

    ServerResponse selectByNameAndProductId(String name, Integer id,Integer pageNum, Integer pageSize);


    ServerResponse<String> setSaleStatus(Integer productId,Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchProduct(String productName,Integer productId,int pageNum,int pageSize);

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy);



}
