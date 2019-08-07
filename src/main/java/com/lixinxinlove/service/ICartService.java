package com.lixinxinlove.service;

import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.vo.CartVO;

public interface ICartService {
    ServerResponse add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVO> update(Integer userId, Integer productId, Integer count);
    ServerResponse<CartVO> deleteProduct(Integer userId,String productIds);

    ServerResponse<CartVO> list (Integer userId);
    ServerResponse<CartVO> selectOrUnSelect (Integer userId,Integer productId,Integer checked);
    ServerResponse<Integer> getCartProductCount(Integer userId);
}
