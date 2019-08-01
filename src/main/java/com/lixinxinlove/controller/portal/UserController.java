package com.lixinxinlove.controller.portal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {


    @RequestMapping("logn")
    public Object login(String userName, String password) {

        return null;
    }

}
