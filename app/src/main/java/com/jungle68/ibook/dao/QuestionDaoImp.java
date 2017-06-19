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

    public List<Question> qureyDataByQuestion(String question) {
        QuestionDao questionDao = getRDaoSession().getQuestionDao();
        return questionDao.queryBuilder()
                .where(QuestionDao.Properties.Question.like(question))
                .limit(DEFAULT_PAGESIZE)
                .orderAsc(QuestionDao.Properties._id)
                .list();
    }

    public List<Question> qureyDataByAnster(String anster) {
        QuestionDao questionDao = getRDaoSession().getQuestionDao();
        return questionDao.queryBuilder()
                .whereOr(QuestionDao.Properties.Itema.like(anster)
                        , QuestionDao.Properties.Itemb.like(anster)
                        , QuestionDao.Properties.Itemc.like(anster)
                        , QuestionDao.Properties.Itemd.like(anster))
                .limit(DEFAULT_PAGESIZE)
                .orderAsc(QuestionDao.Properties._id)
                .list();
    }

    public List<Question> qureyDataById(String id) {
        QuestionDao questionDao = getRDaoSession().getQuestionDao();
        return questionDao.queryBuilder()
                .where(QuestionDao.Properties.Id.like(id))
                .limit(DEFAULT_PAGESIZE)
                .orderAsc(QuestionDao.Properties._id)
                .list();
    }

    public List<Question> qureyDataByName(String name) {
        QuestionDao questionDao = getRDaoSession().getQuestionDao();
        return questionDao.queryBuilder()
                .where(QuestionDao.Properties.Name.like(name))
                .limit(DEFAULT_PAGESIZE)
                .orderAsc(QuestionDao.Properties._id)
                .list();
    }

    public List<Question> qureyDataByfencename(String fencename) {
        QuestionDao questionDao = getRDaoSession().getQuestionDao();
        return questionDao.queryBuilder()
                .where(QuestionDao.Properties.Fencename.like(fencename))
                .limit(DEFAULT_PAGESIZE)
                .orderAsc(QuestionDao.Properties._id)
                .list();
    }

    public List<Question> qureyDataByRule(String rule) {
        QuestionDao questionDao = getRDaoSession().getQuestionDao();
        return questionDao.queryBuilder()
                .where(QuestionDao.Properties.Rule.like(rule))
                .limit(DEFAULT_PAGESIZE)
                .orderAsc(QuestionDao.Properties._id)
                .list();
    }

}
