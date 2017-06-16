package com.jungle68.ibook.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.jungle68.ibook.R;
import com.jungle68.ibook.bean.Question;
import com.jungle68.ibook.dao.QuestionDaoImp;
import com.jungle68.ibook.utils.SharePreferenceUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.cb_a)
    CheckBox mCbA;
    @BindView(R.id.cb_b)
    CheckBox mCbB;
    @BindView(R.id.cb_c)
    CheckBox mCbC;
    @BindView(R.id.cb_d)
    CheckBox mCbD;
    @BindView(R.id.tv_anster)
    TextView mTvAnster;
    @BindView(R.id.bt_pre)
    Button mBtPre;
    @BindView(R.id.bt_next)
    Button mBtNext;
    @BindView(R.id.pb_loading)
    AVLoadingIndicatorView mPbLoading;

    private List<Question> mListData = new ArrayList<>();
    private long mCurrentId = 0;
    QuestionDaoImp mQusetionDaoImp;
    int mCurrentListPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mPbLoading.hide();
        initData();
    }

    private void initData() {
        mCurrentId = SharePreferenceUtils.getLong(getApplicationContext(), SharePreferenceUtils.TAG_MCURRENTID);
        mQusetionDaoImp = new QuestionDaoImp(getApplication());
        getData(false);
    }

    private void getData(boolean isNext) {
        long _id = 0;
        if (mListData.isEmpty()) {
            _id = mCurrentId;
            mListData.addAll(mQusetionDaoImp.getDataBy_id(_id));
            if (mListData.isEmpty()) {
                Toast.makeText(MainActivity.this, "当前题库是空的！", Toast.LENGTH_SHORT).show();
            } else {
                updateQuestion(mListData.get(mCurrentListPosition));
            }
        } else {
            // 判断是往前还是往后
            if (isNext) {
                _id = mListData.get(mListData.size() - 1).get_id();
                List<Question> tmp = mQusetionDaoImp.getDataBy_id(++_id);
                if (tmp.isEmpty()) {
                    Toast.makeText(MainActivity.this, "没有更多了", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mListData.addAll(tmp);
                    mCurrentListPosition++;
                }
            } else {
                _id = mListData.get(0).get_id();
                List<Question> tmp = mQusetionDaoImp.getDataBy_idDesc(--_id);
                if (tmp.isEmpty()) {
                    Toast.makeText(MainActivity.this, "没有更多了", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mCurrentListPosition = tmp.size() - 1;
                }
                tmp.addAll(mListData);
                mListData.clear();
                mListData.addAll(tmp);
            }
            displayData();
        }


    }

    private void displayData() {
        if (mListData.isEmpty()) {
            Toast.makeText(MainActivity.this, "当前题库是空的！", Toast.LENGTH_SHORT).show();
        } else {
            updateQuestion(mListData.get(mCurrentListPosition));
        }
    }

    private void updateQuestion(Question question) {

        mTvNumber.setText("[" + question.get_id() + "]" + question.getId() + "、" + question.getQuestion());
        mTvAnster.setText("答案：" + question.getAnser());
        mCbA.setVisibility(View.VISIBLE);
        mCbB.setVisibility(View.VISIBLE);
        mCbC.setVisibility(View.VISIBLE);
        mCbD.setVisibility(View.VISIBLE);
        mCbA.setText(question.getItema());
        mCbB.setText(question.getItemb());
        mCbC.setText(question.getItemc());
        mCbD.setText(question.getItemd());
        mPbLoading.hide();
        mCurrentId = question.get_id();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.bt_pre, R.id.bt_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_pre:
                if (mCurrentId <= 1 || mListData.isEmpty()) {
                    Toast.makeText(MainActivity.this, "没有更多了", Toast.LENGTH_SHORT).show();
                } else {
                    if (mCurrentId == mListData.get(0).get_id()) {
                        getData(false);
                    } else {
                        updateQuestion(mListData.get(--mCurrentListPosition));
                    }
                }
                break;
            case R.id.bt_next:
                if (mCurrentId == mListData.get(mListData.size() - 1).get_id()) {
                    getData(true);
                } else {
                    updateQuestion(mListData.get(++mCurrentListPosition));
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharePreferenceUtils.saveLong(getApplicationContext(), SharePreferenceUtils.TAG_MCURRENTID, mCurrentId);
    }
}
