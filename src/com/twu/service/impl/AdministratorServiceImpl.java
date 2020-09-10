package com.twu.service.impl;


import com.twu.constant.SystemMessageConstant;
import com.twu.database.EventData;
import com.twu.database.UserData;
import com.twu.model.User;
import com.twu.service.AdministratorService;
import com.twu.utils.SystemUtils;

import java.util.Scanner;

/**
 * desc : 管理员功能实现类
 * date : 2020-09-03
 * @author xiaowen.cqu
 */

public class AdministratorServiceImpl implements AdministratorService {
    UserServiceImpl userService =new UserServiceImpl();
    Scanner sc =new Scanner(System.in);

//    /*管理员登陆*/
    @Override
    public void administratorLogin(String username, String password ){
        for(User user : UserData.userList){
            if(user.getUserName().equals(username) && user.getPassword().equals(password)){
                SystemUtils.loginUser = user;
                break;
            }
        }
    }

    /*查看热搜排行榜*/
    @Override
    public void check_HotSearch() {
        userService.check();

    }

    /*添加热搜*/
    @Override
    public void add_HotSearch() {
        userService.add();
    }

    /*添加超级热搜*/
    @Override
    public void add_SuperHotSearch() {
        System.out.println(SystemMessageConstant.SUPER_HOTSEARCH_TITLE);
        String title = sc.nextLine();
        if(UserServiceImpl.containEvent(title)) {
            int eventIndex = UserServiceImpl.findEvent(title);
            EventData.search_list.get(eventIndex).setSuperHot(true);
        }
        else {
            System.out.println(SystemMessageConstant.ADD_HOTSEARCH_FAIL);
        }
    }

}
