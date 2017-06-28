package com.jungle68.ibook.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jungle68.ibook.R;
import com.jungle68.ibook.bean.Question;
import com.jungle68.ibook.dao.QuestionDaoImp;
import com.jungle68.ibook.utils.LinearDecoration;
import com.jungle68.ibook.utils.SharePreferenceUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements MultiItemTypeAdapter.OnItemClickListener {
    public static final String KEY_TYPE = "type";

    public static final int SEARCH_TYPE_ID = 0;
    public static final int SEARCH_TYPE_NO = 1;
    public static final int SEARCH_TYPE_NAME = 2;
    public static final int SEARCH_TYPE_FENCE_NAME = 3;
    public static final int SEARCH_TYPE_FENCE_RULE = 4;
    public static final int SEARCH_TYPE_QUSTION = 5;
    public static final int SEARCH_TYPE_ANSTER = 6;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_lsit)
    RecyclerView rvLsit;


    private int mType;
    private QuestionDaoImp questionDaoImp;
    private List<Question> mDatas = new ArrayList<>();
    private CommonAdapter mAdapter;
    private EmptyWrapper mEmptyWrapper;

    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        //设置我们的ToolBar
        setSupportActionBar(toolbar);

        questionDaoImp = new QuestionDaoImp(getApplication());
        mType = getIntent().getIntExtra(KEY_TYPE, SEARCH_TYPE_ID);
        initReCycleView();
    }

    private void doSearch(String query) {
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
        mEmptyWrapper.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doSearch(query);
                // Toast like print
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });

        return true;
    }

    private void initReCycleView() {
        rvLsit.setHasFixedSize(true);
        rvLsit.setLayoutManager(new LinearLayoutManager(this));
        rvLsit.addItemDecoration(new LinearDecoration(60, 0, 0, 0));
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
        mEmptyWrapper = new EmptyWrapper(mAdapter);
        mEmptyWrapper.setEmptyView(R.layout.view_empty);
        rvLsit.setAdapter(mEmptyWrapper);
        mAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, final int position) {
        mDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.choose_qustion_dialog_title)
                .setMessage(R.string.choose_qustion_dialog_message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharePreferenceUtils.saveLong(getApplicationContext(), SharePreferenceUtils.TAG_MCURRENTID, mDatas.get(position).get_id());
                        System.out.println("question = " + mDatas.get(position).get_id());
                        mDialog.dismiss();
                        startActivity(new Intent(SearchActivity.this,MainActivity.class));
                        finish();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                })
                .create();
        mDialog.show();
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
