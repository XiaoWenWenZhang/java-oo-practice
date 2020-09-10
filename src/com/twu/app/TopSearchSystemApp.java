package com.twu.app;


import com.twu.constant.SystemMessageConstant;
import com.twu.service.AdministratorService;
import com.twu.service.UserService;
import com.twu.service.impl.AdministratorServiceImpl;
import com.twu.service.impl.UserServiceImpl;
import com.twu.utils.SystemUtils;

import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * desc : 热搜系统入口
 * date : 2020-09-04
 * @author xiaowen.cqu
 */
public class TopSearchSystemApp {

    private static UserService userService =new UserServiceImpl();
    private static AdministratorService administratorService = new AdministratorServiceImpl();

    private static Scanner sc =new Scanner(System.in);
    private static boolean exitSystem = false;
    public static void main(String[] args) {

        loginOperation();
        userOperation();
    }


    //登录动作，登录失败将反复登录
    public static void loginOperation(){

        boolean loginResult = true;
        while(loginResult){
            System.out.println(SystemMessageConstant.WELCOME_MESS);
            System.out.println(SystemMessageConstant.LOGIN_USERID_REMIND);
            String username = sc.nextLine();
            System.out.println(SystemMessageConstant.LOGIN_PASSWORD_REMIND);
            String password = sc.nextLine();
            userService.userLogin(username,password);
            //如果全局变量 SystemUtils.loginUser 里面有对应的信息 说明登录成功
            if(SystemUtils.loginUser != null && SystemUtils.loginUser.getUserType() == 1 ||
                    SystemUtils.loginUser.getUserType() ==2){
                loginResult = false;
                SystemUtils.loginType = SystemUtils.loginUser.getUserType();
                System.out.println(SystemMessageConstant.LOGIN_SUCCESS + SystemUtils.loginUser.getUserName());
                return;
            }
            System.out.println(SystemMessageConstant.LOGIN_FAIL);
        }

    }


    //登录用户操作
    private static void userOperation() {

        System.out.println(SystemMessageConstant.AUTHORITY_MESS);

        if(SystemUtils.loginType == 1){
            System.out.println(SystemMessageConstant.USER_AUTHORITY);

            userSelectNum();

        }
        else{
            System.out.println(SystemMessageConstant.MANAGER_AUTHORITY);

            managerSelectNUm();
        }

    }



    //用户选择数字进行操作
      /*  1.查看热搜排行
          2.给热搜事件投票
          3.购买热搜
          4.添加热搜
          5.退出*/
    private static void userSelectNum() {
        boolean selectContinue = true;
        while (selectContinue){
            System.out.println(SystemMessageConstant.AUTHORITY_INPUT);
            String operationString= sc.nextLine();
            if(!isInteger(operationString) || operationString.length() <= 0){
                System.out.println(SystemMessageConstant.NUM_INPUT);
                continue;
            }
            int operationNum = Integer.parseInt(operationString);
            if(operationNum > 5 || operationNum < 0){
                System.out.println(SystemMessageConstant.CORRECT_NUM_INPUT);
            }
            else{
                // 各种操作
                switch (operationNum){
                    case 1 :
                        userService.check();
                        break;
                    case 2 :
                        userService.vote( );
                        break;
                    case 3 :
                        userService.buy();
                        break;
                    case 4 :
                        userService.add();
                        break;
                    case 5 :
                        System.exit(0);
                }
            }
        }
    }

    // 方法与userSelectNum相似 管理员操作
    private static void managerSelectNUm() {
        boolean selectContinue = true;
        while (selectContinue){
            System.out.println(SystemMessageConstant.AUTHORITY_INPUT);
            String operationString= sc.nextLine();
            if(!isInteger(operationString)){
                System.out.println(SystemMessageConstant.NUM_INPUT);
            }
            int operationNum = Integer.parseInt(operationString);
            if(operationNum > 5 || operationNum < 0){
                System.out.println(SystemMessageConstant.CORRECT_NUM_INPUT);
            }
            else{
                // 各种操作
                switch (operationNum) {
                    case 1 :
                        administratorService.check_HotSearch();
                        break;
                    case 2 :
                        administratorService.add_HotSearch();
                        break;
                    case 3 :
                        administratorService.add_SuperHotSearch();
                        break;

                    case 4 :
                        System.exit(0);
                }

            }
        }
    }


    //字符串数字判断
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}