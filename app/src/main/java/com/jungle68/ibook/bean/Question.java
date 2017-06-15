package com.jungle68.ibook.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jungle on 2017/6/14.
 */
@Entity
public class Question {

    /**
     * id : jc00001
     * name : 变电检测管理规定
     * fencename : 管理规定
     * rule : 第八条
     * question : 根据变电检测管理规定，省公司状态评价中心履行以下职责（  ）。
     * itema : A.协助市公司运检部做好变电设备检测管理工作
     * itemb : B.按照省公司检测计划开展检测工作
     * itemc : C.负责变电站设备检测重大异常数据分析和复测
     * itemd : D.负责开展检测装备性能检测
     * anser : BCD
     */
    @Id
    private Long _id;
    private String id;
    private String name;
    private String fencename;
    private String rule;
    private String question;
    private String itema;
    private String itemb;
    private String itemc;
    private String itemd;
    private String anser;

    @Generated(hash = 758321165)
    public Question(Long _id, String id, String name, String fencename, String rule,
            String question, String itema, String itemb, String itemc, String itemd,
            String anser) {
        this._id = _id;
        this.id = id;
        this.name = name;
        this.fencename = fencename;
        this.rule = rule;
        this.question = question;
        this.itema = itema;
        this.itemb = itemb;
        this.itemc = itemc;
        this.itemd = itemd;
        this.anser = anser;
    }

    @Generated(hash = 1868476517)
    public Question() {
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFencename() {
        return fencename;
    }

    public void setFencename(String fencename) {
        this.fencename = fencename;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getItema() {
        return itema;
    }

    public void setItema(String itema) {
        this.itema = itema;
    }

    public String getItemb() {
        return itemb;
    }

    public void setItemb(String itemb) {
        this.itemb = itemb;
    }

    public String getItemc() {
        return itemc;
    }

    public void setItemc(String itemc) {
        this.itemc = itemc;
    }

    public String getItemd() {
        return itemd;
    }

    public void setItemd(String itemd) {
        this.itemd = itemd;
    }

    public String getAnser() {
        return anser;
    }

    public void setAnser(String anser) {
        this.anser = anser;
    }
}
