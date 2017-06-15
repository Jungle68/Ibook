package com.jungle68.ibook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jungle68.ibook.R;
import com.jungle68.ibook.bean.Question;
import com.jungle68.ibook.dao.QuestionDaoImp;
import com.jungle68.ibook.utils.FileUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class GuideActivity extends AppCompatActivity {

    private static final long DEFAULT_DELAY_TIME = 4;
    @BindView(R.id.pb_loading)
    AVLoadingIndicatorView mPbLoading;
    QuestionDaoImp mQusetionDaoImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        mQusetionDaoImp = new QuestionDaoImp(getApplication());
        mPbLoading.setVisibility(View.VISIBLE);
        mPbLoading.show();
        initData();
    }

    private void initData() {
        if (mQusetionDaoImp.getDataBy_id(0).isEmpty()) {


            Observable.empty()
                    .observeOn(Schedulers.io())
                    .subscribe(new Subscriber<Object>() {
                        @Override
                        public void onCompleted() {
                            handleData("q1.json");
                            handleData("q2.json");
                            handleData("q3.json");
                            handleData("q4.json");
                            Observable.just(mQusetionDaoImp.getDataBy_id(0))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Action1<List<Question>>() {
                                        @Override
                                        public void call(List<Question> questions) {
                                            if (questions.isEmpty()) {
                                                showError();
                                            } else {
                                                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                                                finish();
                                            }
                                        }
                                    });
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            showError();
                        }

                        @Override
                        public void onNext(Object o) {
                        }
                    });
        } else {
            startActivity(new Intent(GuideActivity.this, MainActivity.class));
            finish();
        }

    }

    private void handleData(String localFileName) {
        String s = getStrFromAssets(localFileName);
        List<Question> mListData;
        if (!TextUtils.isEmpty(s)) {
            mListData = new Gson().fromJson(s, new TypeToken<List<Question>>() {
            }.getType());
            mQusetionDaoImp.inserOrUpdate(mListData);
        }
    }

    private void showError() {
        mPbLoading.hide();
        Toast.makeText(GuideActivity.this, "加载出现错误，请重新启动应用", Toast.LENGTH_SHORT).show();
        Observable.timer(DEFAULT_DELAY_TIME, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.exit(0);
                    }
                });
    }


    /**
     * @return Json数据（String）
     * @description 通过assets文件获取json数据，这里写的十分简单，没做循环判断。
     */
    private String getStrFromAssets(String name) {
        String strData = null;
        try {
            strData = FileUtils.readFileToString(getAssets().open(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strData;
    }
}
