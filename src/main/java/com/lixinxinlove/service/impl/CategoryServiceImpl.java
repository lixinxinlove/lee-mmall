package com.lixinxinlove.service.impl;

import com.lixinxinlove.common.ResponseCode;
import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.dao.CategoryMapper;
import com.lixinxinlove.pojo.Category;
import com.lixinxinlove.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse<Category> getCategoryList(Integer parentId) {
        List<Category> list = categoryMapper.selectByParentId(parentId);
        ServerResponse serverResponse = ServerResponse.createBySuccess(list);
        return serverResponse;
    }

    @Override
    public ServerResponse<Category> AddCategory(Category category, Integer parentId) {

        category.setParentId(parentId);
        int f = categoryMapper.insertSelective(category);

        if (f > 0) {
            return ServerResponse.createBySuccess();
        } else {
            return ServerResponse.createByErrorMessage("添加失败");
        }
    }

    @Override
    public ServerResponse<Category> setCategoryName(Integer categoryId, String categoryName) {


        Category category=new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int f = categoryMapper.updateByPrimaryKeySelective(category);

        if (f > 0) {
            return ServerResponse.createBySuccess();
        } else {
            return ServerResponse.createByErrorMessage("更新失败");
        }
    }

    @Override
    public ServerResponse getDeepCategory(Integer categoryId) {

        return ServerResponse.createBySuccess(categoryMapper.selectChrenIds(categoryId));
    }
}
