package com.lixinxinlove.service;

import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.pojo.Category;

import java.util.List;

public interface ICategoryService {

    ServerResponse<Category> getCategoryList(Integer parentId);

    ServerResponse<Category> AddCategory(Category category, Integer parentId);

    ServerResponse<Category> setCategoryName(Integer categoryId, String categoryName);

    ServerResponse<Category> getDeepCategory(Integer categoryId);


    ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse updateCategoryName(Integer categoryId,String categoryName);
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

}
