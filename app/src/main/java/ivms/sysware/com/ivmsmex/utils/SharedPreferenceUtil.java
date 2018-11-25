package ivms.sysware.com.ivmsmex.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {
    private static final String SETTINGS_NAME = "PREFS";
    private static SharedPreferenceUtil sharedPreferenceUtil = null;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    private boolean mBulkUpdate = false;


    private SharedPreferenceUtil(Context context) {
        if (context != null) {
            mPrefs = MyApplication.getAppContext().getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
            mEditor = mPrefs.edit();
        }
    }

    public static SharedPreferenceUtil getInstance(Context context){
        if (sharedPreferenceUtil == null) {
            sharedPreferenceUtil = new SharedPreferenceUtil(context);
        }
        return sharedPreferenceUtil;
    }

    public enum Key {
        idUser,
        idVehicle,
        nameUser,
        platesVehicle,
        bLogin
    }

    //PUT
    public void put(Key key, String val) {
        doEdit();
        mEditor.putString(key.name(), val);
        doCommit();
    }

    public void put(Key key, int val) {
        doEdit();
        mEditor.putInt(key.name(), val);
        doCommit();
    }

    public void put(Key key, boolean val) {
        doEdit();
        mEditor.putBoolean(key.name(), val);
        doCommit();
    }

    public void put(Key key, float val) {
        doEdit();
        mEditor.putFloat(key.name(), val);
        doCommit();
    }

    //GET
    public String getString(Key key, String defaultValue) {
        return mPrefs.getString(key.name(), defaultValue);
    }

    public String getString(Key key) {
        return mPrefs.getString(key.name(), null);
    }

    public int getInt(Key key) {
        return mPrefs.getInt(key.name(), 0);
    }

    public int getInt(Key key, int defaultValue) {
        return mPrefs.getInt(key.name(), defaultValue);
    }

    public long getLong(Key key) {
        return mPrefs.getLong(key.name(), 0);
    }

    public long getLong(Key key, long defaultValue) {
        return mPrefs.getLong(key.name(), defaultValue);
    }

    public float getFloat(Key key) {
        return mPrefs.getFloat(key.name(), 0);
    }

    public float getFloat(Key key, float defaultValue) {
        return mPrefs.getFloat(key.name(), defaultValue);
    }

    public boolean getBoolean(Key key) {
        return mPrefs.getBoolean(key.name(), false);
    }



    public void clear() {
        doEdit();
        mEditor.clear();
        doCommit();
    }

    public void edit() {
        mBulkUpdate = true;
        mEditor = mPrefs.edit();
    }

    public void commit() {
        mBulkUpdate = false;
        mEditor.commit();
        mEditor = null;
    }

    private void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPrefs.edit();
        }
    }

    private void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }
}
