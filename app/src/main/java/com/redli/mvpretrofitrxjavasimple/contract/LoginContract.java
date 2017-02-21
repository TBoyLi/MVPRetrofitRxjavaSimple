package com.redli.mvpretrofitrxjavasimple.contract;

/**
 * Created by redli on 2017/2/14.
 */

public class LoginContract {
    public interface View{
        //显示进度条
        void showProgressBar(boolean enable);
        //登录成功 跳转界面
        void jumpActivity();
        //登录失败 提示用户
        void showLoginFail();
    }

    public interface Presenter{
        //通知model访问服务器登录
        void doLogin(String name, String password);
    }

    public interface Model{
        //访问服务器登录
        boolean login(String name, String password);
    }
}