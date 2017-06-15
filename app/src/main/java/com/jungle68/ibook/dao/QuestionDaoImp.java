package com.jungle68.ibook.dao;

import android.app.Application;

import com.jungle68.ibook.bean.Question;
import com.jungle68.ibook.bean.QuestionDao;

import java.util.List;

/**
 * Created by jungle on 2017/6/15.
 */
public class QuestionDaoImp extends BaseDao {
    public static final int DEFAULT_PAGESIZE = 50;

    private Application mApplication;

    public QuestionDaoImp(Application mApplication) {
        super(mApplication);
        this.mApplication = mApplication;
        // get the note DAO
    }

    public void inserOrUpdate(List<Question> data) {
        QuestionDao questionDao = getWDaoSession().getQuestionDao();
        questionDao.insertOrReplaceInTx(data);
    }

    public List<Question> getDataBy_id(long _id) {
        QuestionDao questionDao = getRDaoSession().getQuestionDao();
        return questionDao.queryBuilder()
                .where(QuestionDao.Properties._id.ge(_id))
                .limit(DEFAULT_PAGESIZE)
                .list();
    }
    public List<Question> getDataBy_idDesc(long _id) {
        QuestionDao questionDao = getRDaoSession().getQuestionDao();
        return questionDao.queryBuilder()
                .where(QuestionDao.Properties._id.le(_id))
                .limit(DEFAULT_PAGESIZE)
                .orderAsc(QuestionDao.Properties._id)
                .list();
    }
}
