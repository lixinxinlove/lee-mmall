package com.lixinxinlove.controller.backend;


import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.pojo.Category;
import com.lixinxinlove.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manager/category")
public class CategoryManageController {


    @Autowired
    private ICategoryService iCategoryService;


    @RequestMapping(value = "get_category", method = RequestMethod.GET)
    public ServerResponse<Category> getCategory(@RequestParam(value = "parentId", defaultValue = "0") Integer parentId) {

        return iCategoryService.getCategoryList(parentId);
    }


    @RequestMapping(value = "add_category", method = RequestMethod.GET)
    public ServerResponse<Category> addCategory(Category category, @RequestParam(value = "parentId", defaultValue = "0") Integer parentId) {

        return iCategoryService.AddCategory(category, parentId);
    }

    @RequestMapping(value = "set_category_name", method = RequestMethod.GET)
    public ServerResponse<Category> setCategoryName(Integer categoryId, String categoryName) {

        return iCategoryService.setCategoryName(categoryId, categoryName);
    }



    @RequestMapping(value = "get_deep_category", method = RequestMethod.GET)
    public ServerResponse<Category> get_deep_category(@RequestParam(value ="categoryId",defaultValue = "0") Integer categoryId) {

        return iCategoryService.getDeepCategory(categoryId);
    }


}
