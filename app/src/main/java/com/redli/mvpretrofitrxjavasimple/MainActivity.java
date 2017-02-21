package com.redli.mvpretrofitrxjavasimple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.redli.mvpretrofitrxjavasimple.utils.RxBus;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private Subscription rxSbscription;

    @OnClick(R.id.mvp_login)
    public void mvpLogin(){
        startActivity(new Intent(this, LoginActivity.class));
    }
    @OnClick(R.id.rxjava)
    public void rxjava(){

    }
    @OnClick(R.id.rxbus)
    public void rxbus(){
        startActivity(new Intent(this, RxBusActivity.class));
    }
    @OnClick(R.id.combine)
    public void combine(){
        startActivity(new Intent(this, CombineActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        rxSbscription = RxBus.getInstance().toObservable(StudentEvent.class)
                .subscribe(new Action1<StudentEvent>() {
                    @Override
                    public void call(StudentEvent studentEvent) {
//                        textView.setText("id:"+ studentEvent.getId()+"  name:"+ studentEvent.getName());
                        Toast.makeText(MainActivity.this, "id:"+ studentEvent.getId()+"  name:"+ studentEvent.getName(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onDestroy() {
        if (!rxSbscription.isUnsubscribed()){
            rxSbscription.unsubscribe();
        }
        super.onDestroy();
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        doExitApp();
    }
}
