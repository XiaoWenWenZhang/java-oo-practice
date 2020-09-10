package com.twu.utils;


import com.twu.model.User;

public class SystemUtils {

    /**
     * 保存当前系统登录用户
     */
    public static User loginUser = new User("111","111",3);

    /**
     * 保存当前登录的用户类型  普通用户 or 管理员
     */
    public static int loginType;

}
