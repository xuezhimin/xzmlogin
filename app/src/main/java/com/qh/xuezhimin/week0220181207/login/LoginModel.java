package com.qh.xuezhimin.week0220181207.login;

import com.google.gson.Gson;
import com.qh.xuezhimin.week0220181207.bean.User;
import com.qh.xuezhimin.week0220181207.httputils.Utils;

public class LoginModel {

    public static User login(String name, String pwd) {
        String loginData = Utils.get("http://www.xieast.com/api/user/login.php?username="
                + name + "&password=" + pwd);
        Gson gson = new Gson();
        User user = gson.fromJson(loginData, User.class);
        return user;

    }

}
