package com.redli.mvpretrofitrxjavasimple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.redli.mvpretrofitrxjavasimple.utils.RxBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by redli on 2017/2/15.
 */
public class RxBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.send)
    public void onClick(){
        RxBus.getInstance().post(new StudentEvent("007","小明"));
//        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
