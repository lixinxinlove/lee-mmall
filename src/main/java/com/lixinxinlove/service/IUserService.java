package com.lixinxinlove.service;

import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.pojo.User;

public interface IUserService {

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    /**
     * 校验 用户名 邮箱
     * @param str
     * @param type
     * @return
     */
    ServerResponse<String> checkValid(String str,String type);

    /**
     * 通过用户名查用用户问题
     * @param username
     * @return
     */
    ServerResponse<String> selectQuestion(String username);


    ServerResponse checkAdminRole(User user);

}
