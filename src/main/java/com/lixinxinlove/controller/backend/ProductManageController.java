package com.lixinxinlove.controller.backend;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixinxinlove.common.Const;
import com.lixinxinlove.common.ResponseCode;
import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.dao.ProductMapper;
import com.lixinxinlove.pojo.Product;
import com.lixinxinlove.pojo.User;
import com.lixinxinlove.service.IProductService;
import com.lixinxinlove.service.IUserService;
import com.lixinxinlove.vo.ProductListVO;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.ibatis.annotations.Arg;
import org.springframework.asm.SpringAsmInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
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


    @RequestMapping("list")
    public ServerResponse getList(HttpSession session,
                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "2") int pageSize) {

        return iProductService.getList(pageNum, pageSize);
    }


    @RequestMapping("search")
    public ServerResponse searchProduct(HttpSession session, String productName, Integer productId,
                                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "2") int pageSize) {

        return iProductService.selectByNameAndProductId(productName, productId, pageNum, pageSize);

    }

}
