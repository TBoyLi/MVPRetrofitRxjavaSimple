package com.redli.mvpretrofitrxjavasimple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.redli.mvpretrofitrxjavasimple.utils.HttpMethods;
import com.redli.mvpretrofitrxjavasimple.utils.ProgressSubscriber;
import com.redli.mvpretrofitrxjavasimple.utils.SubscriberOnNextListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by redli on 2017/2/14.
 */
public class CombineActivity extends AppCompatActivity {

    @BindView(R.id.result_TV)
    TextView resultTV;

    @BindView(R.id.click_me_BN)
    Button clickMeBN;
    private Object movie;

    private SubscriberOnNextListener getTopMovieOnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine);
        ButterKnife.bind(this);

        getTopMovieOnNext = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object object) {
                MovieEntity movieEntity = (MovieEntity) object;
                resultTV.setText(movieEntity.getTitle());
            }
        };
    }

    @OnClick(R.id.click_me_BN)
    public void onClick() {
        getMovie();
    }

    //网络获取数据
    public void getMovie() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.movieUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        HttpMethods.MovieService movieService = retrofit.create(HttpMethods.MovieService.class);

        //无rxjava
//        Call<MovieEntity> call = movieService.getTopMovie(0, 10);
//
//        call.enqueue(new Callback<MovieEntity>() {
//            @Override
//            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
//                resultTV.setText(response.body().getTitle());
//            }
//
//            @Override
//            public void onFailure(Call<MovieEntity> call, Throwable t) {
//                resultTV.setText(t.getMessage());
//            }
//        });

        //添加rxjava
//        movieService.getTopMovie(0, 10)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MovieEntity>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(CombineActivity.this, "Get Top Movie Completed", Toast
// .LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        resultTV.setText(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(MovieEntity movieEntity) {
//                        resultTV.setText(movieEntity.getTitle());
//                    }
//                });


        //rxjava 封装
//        HttpMethods.getInstance().getTopMovie(new Subscriber<MovieEntity>() {
//            @Override
//            public void onCompleted() {
//                Toast.makeText(CombineActivity.this, "Get Top Movie Completed", Toast
// .LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                resultTV.setText(e.getMessage());
//            }
//
//            @Override
//            public void onNext(MovieEntity movieEntity) {
//                resultTV.setText(movieEntity.getTitle());
//            }
//        }, 0, 10);
        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber<MovieEntity>(CombineActivity
                .this, getTopMovieOnNext), 0, 10);

    }
}
