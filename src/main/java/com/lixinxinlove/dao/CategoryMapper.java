package com.lixinxinlove.dao;

import com.lixinxinlove.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);


    List<Category> selectByParentId(Integer parentId);

    List<Integer> selectChrenIds(Integer categoryId);


    List<Category> selectCategoryChildrenByParentId(Integer parentId);
}