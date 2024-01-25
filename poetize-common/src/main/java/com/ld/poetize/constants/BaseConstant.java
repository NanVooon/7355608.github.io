package com.ld.poetize.constants;

/**
 * @Author zuosy
 * @Date 2024/1/24 20:00
 **/
public class BaseConstant {

    public static final class OAuth2{
        private OAuth2(){

        }

        /**
         * 密码模式（自定义）
         */
        public static final String GRANT_TYPE_PASSWORD = "authorization_password";

        /**
         * 登录地址
         */
        public static final String LOGIN_URL = "/login";
    }
}
