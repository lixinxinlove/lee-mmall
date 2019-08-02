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

}
