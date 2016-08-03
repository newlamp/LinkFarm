package sinia.com.linkfarm.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import sinia.com.baihangeducation.bean.LoginBean;

/**
 * Created by 忧郁的眼神 on 2016/7/15.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public static Context context;

    private LoginBean login;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        instance = this;
    }

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    public void setBooleanValue(String in_settingName, boolean in_val) {
        SharedPreferences sp = context.getSharedPreferences("is_login",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean(in_settingName, in_val);
        ed.commit();
        ed = null;
        sp = null;
    }

    public void setStringValue(String in_settingName, String in_val) {
        SharedPreferences sp = context.getSharedPreferences("userId",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(in_settingName, in_val);
        ed.commit();
        ed = null;
        sp = null;
    }

    public String getStringValue(String in_settingName) {
        SharedPreferences sp = context.getSharedPreferences("userId",
                Context.MODE_PRIVATE);
        String ret = sp.getString(in_settingName, "");
        sp = null;
        return ret;
    }

    public Boolean getBoolValue(String in_settingName) {
        SharedPreferences sp = context.getSharedPreferences("is_login",
                Context.MODE_PRIVATE);
        boolean ret = sp.getBoolean(in_settingName, false);
        sp = null;
        return ret;
    }

    public LoginBean getLoginBean() {
        if (null == login) {
            LoginBean lb = SaveUtils.getShareObject(context, "LOGIN",
                    "loginbean", LoginBean.class);
            return lb;
        }
        return login;
    }

    public void setLoginBean(LoginBean login) {
        this.login = login;
        if (login != null) {
            SaveUtils.putShareObject(context, "LOGIN", "loginbean", login);
        }
    }
}
