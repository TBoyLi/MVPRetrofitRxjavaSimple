package com.redli.mvpretrofitrxjavasimple.presenter;
import com.redli.mvpretrofitrxjavasimple.contract.LoginContract;
import com.redli.mvpretrofitrxjavasimple.model.LoginModelImpl;

/**
* Created by redli on 2017/02/14
*/

public class LoginPresenterImpl implements LoginContract.Presenter{

    private LoginContract.View loginView;
    private LoginContract.Model loginModel;

    public LoginPresenterImpl(LoginContract.View loginView){
        this.loginView = loginView;
        initPresenter();
    }

    private void initPresenter() {
        loginModel = new LoginModelImpl();
    }

    @Override
    public void doLogin(String name, String password) {
        loginView.showProgressBar(true);
        if (loginModel.login(name, password)){
//            loginView.showProgressBar(false);
            loginView.jumpActivity();
        }else {
//            loginView.showProgressBar(false);
            loginView.showLoginFail();
        }
    }
}