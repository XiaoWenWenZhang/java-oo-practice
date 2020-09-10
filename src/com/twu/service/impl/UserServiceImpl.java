package com.twu.service.impl;


import com.twu.app.TopSearchSystemApp;
import com.twu.constant.SystemMessageConstant;
import com.twu.database.EventData;
import com.twu.database.UserData;
import com.twu.model.Event;
import com.twu.model.User;
import com.twu.service.UserService;
import com.twu.utils.SystemUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * desc : 用户功能实现类
 * date : 2020-09-04
 * @author xiaowen.cqu
 */
public class UserServiceImpl implements UserService {
    Scanner sc =new Scanner(System.in);

    @Override
    public void userLogin(String username, String password) {

        for(User user : UserData.userList){
            if(user.getUserName().equals(username) && user.getPassword().equals(password)){
                SystemUtils.loginUser = user;
                break;
            }
        }

    }

    @Override
    public void check() {
        eventSort();
        int i=1;
        if(!EventData.search_list.isEmpty()){
            for(Event event : EventData.search_list) {
                System.out.println((i++) + ". " + event.getTitle() + ". " + event.getHeat());
            }
        }else {
            System.out.println(SystemMessageConstant.NON_SEARCHEVENT);
        }

    }
    @Override
    public void add() {
        System.out.println(SystemMessageConstant.HOTSEARCH_TITLE);
        String title = sc.nextLine();
        if(containEvent(title)) {
            System.out.println(SystemMessageConstant.ADD_FAIL);
        }
        else {
            Event event =new Event(title);
            EventData.search_list.add(event);
            System.out.println(SystemMessageConstant.ADD_SUCCESS);
        }
    }

    @Override
    public void vote( ) {
        System.out.println(SystemMessageConstant.VOTE_TITLE);
        String title = sc.nextLine();
        if(containEvent(title)){
            System.out.println(SystemMessageConstant.VOTE_AMOUNT+SystemUtils.loginUser.getVote());
            String voteString = sc.nextLine();
            if(TopSearchSystemApp.isInteger(voteString) && voteString.length() > 0){
                int vote =Integer.parseInt(voteString);
                if(vote > SystemUtils.loginUser.getVote()) {
                    System.out.println(SystemMessageConstant.VOTE_FAIL);
                }else {
                    System.out.println(SystemMessageConstant.VOTE+vote);
                    System.out.println(SystemMessageConstant.VOTE_SUCCESS);

                    //是超级热搜加两倍
                    SystemUtils.loginUser.setVote(SystemUtils.loginUser.getVote()-vote);
                    int eventIndex = findEvent(title);

                    int newHeat = 0;
                    if(EventData.search_list.get(eventIndex).isSuperHot()){
                        newHeat = EventData.search_list.get(eventIndex).getHeat() + vote * 2;
                    }
                    else {
                        newHeat = EventData.search_list.get(eventIndex).getHeat() + vote;
                    }

                    EventData.search_list.get(eventIndex).setHeat(newHeat);
                    eventSort();
                }
            }


        }else {
            System.out.println(SystemMessageConstant.VOTE_NON_EXISTENT);
        }

    }

    @Override
    public void buy() {
        System.out.println(SystemMessageConstant.BUY_TITLE);
        String title =sc.nextLine();
        if(containEvent(title)){
            System.out.println(SystemMessageConstant.BUY_RANK);
            String rankString = sc.nextLine();
            if(TopSearchSystemApp.isInteger(rankString) && rankString.length() > 0){
                int rank = Integer.parseInt(rankString);
                Event eventNow = EventData.search_list.get(findEvent(title));
                int nowRank = eventNow.getRank();
                if(rank <= 0 || rank== nowRank || rank > EventData.search_list.size()){
                    System.out.println(SystemMessageConstant.BUY_RANK_FAIL);
                }
                else{
                    System.out.println(SystemMessageConstant.BUY_PRICE);
                    String priceString =sc.nextLine();
                    if(TopSearchSystemApp.isInteger(priceString) && priceString.length() > 0){
                        int price = Integer.parseInt(priceString);
                        if(price<0 || EventData.search_list.get(rank-1).getPrice()>price) {
                            System.out.println(SystemMessageConstant.BUY_FAIL);
                        }else {
                            eventNow.setPrice(price);
                            EventData.search_list.get(findEvent(title)).setHeat(EventData.search_list.get(rank-1).getHeat());
                            EventData.search_list.remove(rank-1);
                            eventSort();
                            System.out.println(SystemMessageConstant.BUY_SUCCESS);
                        }
                    }

                }
            }

        }else {
            System.out.println(SystemMessageConstant.BUY_NON_EXISTENT);
        }
    }

    public  static void eventSort() {
        Collections.sort(EventData.search_list, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
             return o2.getHeat()-o1.getHeat();
            }
        });
        int rankIndex = 1;
        for(Event e: EventData.search_list){
            e.setRank(rankIndex++);
        }
    }

    public static boolean containEvent(String eventName){
        if(EventData.search_list.size() <= 0 ){
            return false;
        }
      for (Event e : EventData.search_list){
          if(eventName.equals(e.getTitle())){
              return  true;
          }
      }
      return false;
    }
    public static int findEvent(String eventName){
        if(EventData.search_list.size() <= 0 ){
            return -1;
        }
        for(int i = 0;i<EventData.search_list.size();i++){
            if(eventName.equals(EventData.search_list.get(i).getTitle())){
                return i;
            }
        }

        return -1;
    }


}