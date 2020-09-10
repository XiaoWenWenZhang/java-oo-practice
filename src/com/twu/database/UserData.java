package com.twu.database;


import com.twu.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * desc : 热搜系统数据库
 * date : 2020-09-04
 * @author xiaowen.cqu
 */
public class UserData {

    // 管理员信息和用户信息全在一个list里面依靠userType区分
    public static List<User> userList = new ArrayList<>();

    /**
     * 1 用户 2 管理员
     */
    static {
        userList.add(new User("Bob" ,"123", 1));
        userList.add(new User("Amy" ,"456", 2));

    }

}
