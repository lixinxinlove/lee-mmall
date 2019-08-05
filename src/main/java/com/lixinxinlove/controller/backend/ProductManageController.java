package com.lixinxinlove.controller.backend;


import com.lixinxinlove.common.Const;
import com.lixinxinlove.common.ResponseCode;
import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.pojo.Product;
import com.lixinxinlove.pojo.User;
import com.lixinxinlove.service.IProductService;
import com.lixinxinlove.service.IUserService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("manage/product")
public class ProductManageController {


    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    @RequestMapping("save")
    public ServerResponse productSave(HttpSession session, Product product) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        if (iUserService.checkAdminRole(user).isSuccess()) {

            return iProductService.saveOrUpdateProduct(product);

        } else {
            return ServerResponse.createByErrorMessage("没有权限");
        }

    }

}
