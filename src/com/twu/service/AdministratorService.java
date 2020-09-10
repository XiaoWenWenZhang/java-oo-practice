package com.twu.service;

public interface AdministratorService {
    void administratorLogin(String username, String password );

    /*查看热搜排行榜*/
    void check_HotSearch();
    /*添加热搜*/
    void add_HotSearch();

    /*添加超级热搜*/
    void add_SuperHotSearch();
}
