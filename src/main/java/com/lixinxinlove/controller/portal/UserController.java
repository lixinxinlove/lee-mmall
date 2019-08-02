package com.lixinxinlove.controller.portal;

import com.lixinxinlove.common.Const;
import com.lixinxinlove.common.ServerResponse;
import com.lixinxinlove.pojo.User;
import com.lixinxinlove.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    private IUserService userService;

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ServerResponse<User> login(String userName, String password, HttpSession session) {

        ServerResponse<User> response = userService.login(userName, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }


    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }


    /**
     * 注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServerResponse<String> register(User user) {
        return userService.register(user);
    }


}
