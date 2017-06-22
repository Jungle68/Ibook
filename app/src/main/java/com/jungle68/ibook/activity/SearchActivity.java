package com.jungle68.ibook.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.jungle68.ibook.R;
import com.jungle68.ibook.bean.Question;
import com.jungle68.ibook.dao.QuestionDaoImp;
import com.jungle68.ibook.utils.LinearDecoration;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {
    public static final String KEY_TYPE = "type";

    public static final int SEARCH_TYPE_ID = 0;
    public static final int SEARCH_TYPE_NO = 1;
    public static final int SEARCH_TYPE_NAME = 2;
    public static final int SEARCH_TYPE_FENCE_NAME = 3;
    public static final int SEARCH_TYPE_FENCE_RULE = 4;
    public static final int SEARCH_TYPE_QUSTION = 5;
    public static final int SEARCH_TYPE_ANSTER = 6;

    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rv_lsit)
    RecyclerView rvLsit;

    private int mType;
    private QuestionDaoImp questionDaoImp;
    private List<Question> mDatas = new ArrayList<>();
    private CommonAdapter<Question> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mType = getIntent().getIntExtra(KEY_TYPE, SEARCH_TYPE_ID);
        //设置我们的ToolBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //设置我们的SearchView
        searchView.setIconifiedByDefault(true);//设置展开后图标的样式,这里只有两种,一种图标在搜索框外,一种在搜索框内
        searchView.onActionViewExpanded();// 写上此句后searchView初始是可以点击输入的状态，如果不写，那么就需要点击下放大镜，才能出现输入框,也就是设置为ToolBar的ActionView，默认展开
        searchView.requestFocus();//输入焦点
        searchView.setSubmitButtonEnabled(true);//添加提交按钮，监听在OnQueryTextListener的onQueryTextSubmit响应
        searchView.setFocusable(true);//将控件设置成可获取焦点状态,默认是无法获取焦点的,只有设置成true,才能获取控件的点击事件
        searchView.setIconified(false);//输入框内icon不显示
        searchView.requestFocusFromTouch();//模拟焦点点击事件

        searchView.setFocusable(false);//禁止弹出输入法，在某些情况下有需要
        searchView.clearFocus();//禁止弹出输入法，在某些情况下有需要

        //事件监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<Question> datas = null;

                switch (mType) {

                    case SEARCH_TYPE_ID:
                        datas = questionDaoImp.qureyDataById(query);
                        break;
                    case SEARCH_TYPE_NO:
                        datas = questionDaoImp.qureyDataById(query);
                        break;
                    case SEARCH_TYPE_NAME:
                        datas = questionDaoImp.qureyDataByName(query);
                        break;
                    case SEARCH_TYPE_FENCE_NAME:
                        datas = questionDaoImp.qureyDataByfencename(query);
                        break;
                    case SEARCH_TYPE_FENCE_RULE:
                        datas = questionDaoImp.qureyDataByRule(query);
                        break;
                    case SEARCH_TYPE_QUSTION:
                        datas = questionDaoImp.qureyDataByQuestion(query);
                        break;
                    case SEARCH_TYPE_ANSTER:
                        datas = questionDaoImp.qureyDataByAnster(query);
                        break;

                }
                mDatas.clear();
                mDatas.addAll(datas);
                mAdapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        initReCycleView();
    }

    private void initReCycleView() {
        rvLsit.setHasFixedSize(true);
        rvLsit.setLayoutManager(new LinearLayoutManager(this));
        rvLsit.addItemDecoration(new LinearDecoration(30, 0, 0, 0));
        mAdapter = new CommonAdapter<Question>(this, R.layout.item_content, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, Question question, int position) {

                viewHolder.setText(R.id.tv_number, "[" + question.get_id() + "]  " + question.getId() + "、" + question.getQuestion());
                viewHolder.setVisible(R.id.cb_a, true);
                viewHolder.setVisible(R.id.cb_b, true);
                viewHolder.setVisible(R.id.cb_c, true);
                viewHolder.setVisible(R.id.cb_d, true);
                viewHolder.setText(R.id.cb_a, question.getItema());
                viewHolder.setText(R.id.cb_b, question.getItemb());
                viewHolder.setText(R.id.cb_c, question.getItemc());
                viewHolder.setText(R.id.cb_d, question.getItemd());
                viewHolder.setText(R.id.tv_anster, "答案：" + question.getAnser());

            }
        };
    }
}
