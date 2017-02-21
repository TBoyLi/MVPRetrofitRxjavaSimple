package com.redli.mvpretrofitrxjavasimple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.redli.mvpretrofitrxjavasimple.contract.LoginContract;
import com.redli.mvpretrofitrxjavasimple.presenter.LoginPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by redli on 2017/2/14.
 */

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private LoginPresenterImpl loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImpl(this);
    }


    @OnClick(R.id.btnLogin)
    public void login() {
        if (TextUtils.isEmpty(name.getText())) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password.getText())) {
            Toast.makeText(this, "请输入用户密码", Toast.LENGTH_SHORT).show();
            return;
        }
        loginPresenter.doLogin(name.getText().toString(), password.getText().toString());
    }

    @Override
    public void showProgressBar(boolean enable) {
        if (enable) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void jumpActivity() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showLoginFail() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }
}
