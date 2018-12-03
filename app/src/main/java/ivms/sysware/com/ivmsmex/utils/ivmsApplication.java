package ivms.sysware.com.ivmsmex.utils;

import android.app.Application;
import android.content.Context;

public class ivmsApplication extends Application {
    private static Context context;

    private static SharedPreferenceUtil sharedPref;

    //Shared Preferences
    private SharedPreferenceUtil sharedPreferenceUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        ivmsApplication.context = getApplicationContext();
        sharedPreferenceUtil = SharedPreferenceUtil.getInstance(this);
        ivmsApplication.sharedPref =sharedPreferenceUtil;
    }


    public static Context getAppContext() {
        return ivmsApplication.context;
    }



    public SharedPreferenceUtil getSharedPreferenceUtil() {
        return sharedPreferenceUtil;
    }
    public static SharedPreferenceUtil getSharedPreference() {
        return sharedPref;
    }




}
