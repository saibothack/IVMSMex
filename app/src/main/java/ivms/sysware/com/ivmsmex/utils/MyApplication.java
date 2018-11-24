package ivms.sysware.com.ivmsmex.utils;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context context;

    //Shared Preferences
    private SharedPreferenceUtil sharedPreferenceUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        sharedPreferenceUtil = SharedPreferenceUtil.getInstance(this);
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

    public SharedPreferenceUtil getSharedPreferenceUtil() {
        return sharedPreferenceUtil;
    }
}

