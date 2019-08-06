package com.lixinxinlove.controller.backend;


import com.lixinxinlove.common.Const;
import com.lixinxinlove.common.ResponseCode;
import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.pojo.Product;
import com.lixinxinlove.pojo.User;
import com.lixinxinlove.service.IProductService;
import com.lixinxinlove.service.IUserService;
import com.lixinxinlove.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("manage/product")
public class ProductManageController {


    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private FileServiceImpl fileService;

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


    //图片上传
    @RequestMapping("upload")
    public ServerResponse upload(MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = fileService.upload(file, path);
        return ServerResponse.createBySuccess(fileName);
    }


}
