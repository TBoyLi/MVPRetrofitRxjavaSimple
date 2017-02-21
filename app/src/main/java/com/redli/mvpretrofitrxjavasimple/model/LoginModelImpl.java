package com.redli.mvpretrofitrxjavasimple.model;
import android.os.Handler;
import android.os.Message;

import com.redli.mvpretrofitrxjavasimple.contract.LoginContract;

/**
* Created by redli on 2017/02/14
*/

public class LoginModelImpl implements LoginContract.Model{

    private Handler handler = new Handler();
    private boolean isSuccess;
    private Handler handler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    isSuccess = true;
                    break;
                case 2:
                    isSuccess = false;
                    break;
            }
        }
    };

    @Override
    public boolean login(String name, final String password) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                if (password.equals("12345")){
                    message.what = 1;
                    handler1.sendMessage(message);
                }else {
                    message.what = 2;
                    handler1.sendMessage(message);
                }
            }
        }, 3000);
        return isSuccess;
    }
}