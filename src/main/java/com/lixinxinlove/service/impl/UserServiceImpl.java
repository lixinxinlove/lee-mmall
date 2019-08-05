package com.lixinxinlove.service.impl;

import com.lixinxinlove.common.Const;
import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.dao.UserMapper;
import com.lixinxinlove.pojo.User;
import com.lixinxinlove.service.IUserService;
import com.lixinxinlove.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public ServerResponse<User> login(String username, String password) {

        int returnCount = userMapper.checkUsername(username);

        if (returnCount == 0) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        String md5Password = MD5Util.getMD5(password);

        User user = userMapper.selectLogin(username, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(StringUtils.EMPTY);

        return ServerResponse.createBySuccess("登录成功", user);

    }

    @Override
    public ServerResponse<String> register(User user) {
        int returnCount = userMapper.checkUsername(user.getUsername());
        if (returnCount > 0) {
            return ServerResponse.createByErrorMessage("用户已经存在");
        }
        returnCount = userMapper.checkEmail(user.getEmail());

        if (returnCount > 0) {
            return ServerResponse.createByErrorMessage("邮箱已经存在");
        }


        user.setRole(Const.Role.ROLE_CUSTOMER);

        user.setPassword(MD5Util.getMD5(user.getPassword()));

        returnCount = userMapper.insert(user);


        if (returnCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }

        return ServerResponse.createBySuccess("注册成功");

    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        return null;
    }

    @Override
    public ServerResponse<String> selectQuestion(String username) {
        String question = userMapper.selectQuestionByUserName(username);
        return ServerResponse.createBySuccess(question);
    }

    @Override
    public ServerResponse checkAdminRole(User user) {

        if (user.getRole() == Const.Role.ROLE_ADMIN) {
            return ServerResponse.createBySuccess();
        } else {
            return ServerResponse.createByError();
        }
    }


}
