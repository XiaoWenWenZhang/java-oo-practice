package com.twu.enums;

/**
 * desc : 权限枚举
 * date : 2020-09-04
 * @author xiaowen.cqu
 */
public enum AuthorityEnum {

     SEARCH_TOP_SEARCH(1,"查看热搜排行榜"),

     ADD_TOP_SEARCH(2,"添加热搜权限"),

     ADD_SUPER_TOP_SEARCH(3,"添加超级热搜权限"),

     BUY_TOP_SEARCH(4,"购买热搜"),

     VOTE_TOP_SEARCH(5,"给热搜事件投票");


     private int code;

     private String desc;

    AuthorityEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
