package com.luguosong._04_structural._07_proxy_pattern;

/**
 * 身份验证类
 * @author luguosong
 * @date 2022/5/27 15:57
 */
public class AccessValidator {
    public boolean validate(String userId){
        System.out.println("在数据库中验证用户'"+userId+"'是否为合法用户？");
        if (userId.equalsIgnoreCase("杨过")){
            System.out.println("登录成功");
            return true;
        }else {
            System.out.println("登录失败");
            return false;
        }
    }
}
