package com.twu.service;

/**
 * desc : 用户功能接口
 * date : 2020-09-04
 * @author xiaowen.cqu
 */
public interface UserService {


      /*用户登录*/
    void userLogin(String username, String password);

    /*查看热搜排行榜*/
    void check();

    /*给热搜事件投票*/
    void vote();

    /*购买热搜*/
    void buy();

    /*添加热搜*/
    void add();




}
