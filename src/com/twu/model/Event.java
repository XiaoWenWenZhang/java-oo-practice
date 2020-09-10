package com.twu.model;



public class Event {

    String title ; //热搜标题
    int rank ; //热搜事件排名



    int heat ; //热搜事件热度
    int price = 0; //购买的热搜金额
    boolean isSuperHot =false;  //是否为超级热搜

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    public void setHeat(int heat) {
        this.heat = heat;
    }

    public int getHeat() {
        return heat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSuperHot() {
        return isSuperHot;
    }

    public void setSuperHot(boolean superHot) {
        isSuperHot = superHot;
    }


    public Event(String title) {
        this.title = title;
    }


    public Event(String title, int rank) {
        this.title = title;
        this.rank = rank;
    }


    public Event(String title, int rank, int price) {
        this.title = title;
        this.rank = rank;
        this.price = price;
    }


    public Event(String title, int rank, int price, boolean isSuperHot) {
        this.title = title;
        this.rank = rank;
        this.price = price;
        this.isSuperHot = isSuperHot;
    }



}
