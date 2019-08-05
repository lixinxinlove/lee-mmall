package com.lixinxinlove.service;

import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.pojo.Category;

public interface ICategoryService {

    ServerResponse<Category> getCategoryList(Integer parentId);

    ServerResponse<Category> AddCategory(Category category, Integer parentId);

    ServerResponse<Category> setCategoryName(Integer categoryId, String categoryName);

    ServerResponse<Category> getDeepCategory(Integer categoryId);

}
