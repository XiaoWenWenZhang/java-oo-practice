package com.twu.model;

/**
 * desc : 用户实体 POJO
 * date : 2020-09-04
 * @author xiaowen.cqu
 */
public class User {

    private String userName;

    private String password;



    private int vote = 10;

    /**
     * 用户种类   普通用户：1  管理员： 2
     */
    private int userType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public void setVote(int vote) {   this.vote = vote; }

    public int getVote() { return vote; }

    public User(String userName, String password, int userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }
}
