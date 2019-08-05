package com.lixinxinlove.service;

import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.pojo.Product;
import org.springframework.stereotype.Service;

public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);

}
