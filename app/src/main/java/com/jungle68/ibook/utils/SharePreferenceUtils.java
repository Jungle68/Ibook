package com.jungle68.ibook.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jungle on 2017/6/15.
 */

public class SharePreferenceUtils {
    private static SharedPreferences mSharedPreferences;
    public static final String SP_NAME = "ibook_name";
    public static final String TAG_MCURRENTID = "mcurrenId";

    /**
     * 存储重要信息到 sharedPreferences；
     *
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 返回存在 sharedPreferences 的信息
     *
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getString(key, null);
    }

    /**
     * 存储重要信息到 sharedPreferences；
     *
     * @param key
     * @param value
     */
    public static void saveLong(Context context, String key, Long value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putLong(key, value).commit();
    }

    /**
     * 返回存在 sharedPreferences 的信息
     *
     * @param key
     * @return
     */
    public static Long getLong(Context context, String key) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getLong(key, 0L);
    }

    /**
     * 存储重要信息到 sharedPreferences；
     *
     * @param key
     * @param value
     */
    public static void setInterger(Context context, String key, int value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * 返回存在 sharedPreferences 的信息
     *
     * @param key
     * @return
     */
    public static int getInterger(Context context, String key) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getInt(key, -1);
    }

    /**
     * 清除某个内容
     */
    public static boolean remove(Context context, String key) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.edit().remove(key).commit();
    }

    /**
     * 清除 shareprefrence
     */
    public static void clearShareprefrence(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().clear().commit();
    }


}