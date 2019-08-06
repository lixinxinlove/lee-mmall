package com.lixinxinlove.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.dao.ProductMapper;
import com.lixinxinlove.pojo.Product;
import com.lixinxinlove.service.IProductService;
import com.lixinxinlove.vo.ProductListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements IProductService {


    @Autowired
    private ProductMapper productMapper;
    @Override
    public ServerResponse saveOrUpdateProduct(Product product) {
        return null;
    }

    @Override
    public ServerResponse getList(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<Product> productList = productMapper.selectList();

        List<ProductListVO> productListVOS = new ArrayList<>();

        for (Product product : productList) {
            ProductListVO vo = getProductListVOByProduct(product);
            productListVOS.add(vo);
        }
        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVOS);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse selectByNameAndProductId(String name, Integer id,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<Product> productList = productMapper.selectByNameAndProductId(name,id);

        List<ProductListVO> productListVOS = new ArrayList<>();

        for (Product product : productList) {
            ProductListVO vo = getProductListVOByProduct(product);
            productListVOS.add(vo);
        }
        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVOS);
        return ServerResponse.createBySuccess(pageInfo);
    }


    private ProductListVO getProductListVOByProduct(Product product) {
        ProductListVO vo = new ProductListVO();
        vo.setId(product.getId());
        vo.setCategoryId(product.getCategoryId());
        vo.setName(product.getName());
        return vo;
    }


}
