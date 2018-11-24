package ivms.sysware.com.ivmsmex.activities.login;

import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ivms.sysware.com.ivmsmex.activities.navigation.NavigationActivity;
import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.BaseActivity;
import ivms.sysware.com.ivmsmex.activities.rememberPassword.RememberPasswordActivity;
import ivms.sysware.com.ivmsmex.utils.SharedPreferenceUtil;

public class LoginActivity extends BaseActivity {
    private SharedPreferenceUtil sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initComponents();
    }

    @Override
    public void initComponents() {
        sharedPreferences = getMyApplication().getSharedPreferenceUtil();
    }

    @OnClick(R.id.btnLogin)
    void onLoginClick(View view){
        sharedPreferences.put(SharedPreferenceUtil.Key.idUser, 1);
        sharedPreferences.put(SharedPreferenceUtil.Key.idVehicle, 1);
        sharedPreferences.put(SharedPreferenceUtil.Key.nameUser, "Gad Arenas");
        sharedPreferences.put(SharedPreferenceUtil.Key.platesVehicle, "MAF8899");
        redirect(NavigationActivity.class, true);
    }

    @OnClick(R.id.btnRememberPassword)
    void onRememberPasswordClick(View view){
        redirect(RememberPasswordActivity.class);
    }
}
