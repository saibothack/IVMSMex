package ivms.sysware.com.ivmsmex.activities.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ivms.sysware.com.ivmsmex.activities.navigation.NavigationActivity;
import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.BaseActivity;
import ivms.sysware.com.ivmsmex.activities.rememberPassword.RememberPasswordActivity;
import ivms.sysware.com.ivmsmex.services.security.LoginService;
import ivms.sysware.com.ivmsmex.services.security.User;
import ivms.sysware.com.ivmsmex.utils.SharedPreferenceUtil;
import ivms.sysware.com.ivmsmex.utils.delegates.loginListener;
import ivms.sysware.com.ivmsmex.utils.delegates.networkingListener;
import ivms.sysware.com.ivmsmex.utils.enums;

public class LoginActivity extends BaseActivity {
    private SharedPreferenceUtil sharedPreferences;

    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    private loginListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initEvents();
        initComponents();
    }

    @Override
    public void initEvents(){
        LoginService.setExternalNetworkListener(new networkingListener() {
            @Override
            public void onLoaded() {
                closeLoading();
            }

            @Override
            public void onError(String messageError) {
                message("Inicio de Sesión", messageError, enums.MessageType.DANGER);
            }

            @Override
            public void onLoading(String loadingMessage) {
                loading(loadingMessage);
            }
        });

        listener=new loginListener() {
            @Override
            public void onSuccesLogin(User user) {
                LoginService.removeEventListener(listener);
                sharedPreferences.put(SharedPreferenceUtil.Key.bLogin, true);
                sharedPreferences.put(SharedPreferenceUtil.Key.idUser, user.getUserId());
                sharedPreferences.put(SharedPreferenceUtil.Key.idVehicle, user.getVehicle().get(0).getVehicleId());
                sharedPreferences.put(SharedPreferenceUtil.Key.nameUser, user.getNameUser());
                sharedPreferences.put(SharedPreferenceUtil.Key.platesVehicle, user.getVehicle().get(0).getVehiclePlates());
                redirect(NavigationActivity.class, true);
            }

            @Override
            public void onLoginFailed(String errorMessage) {
                message("Inicio de Sesión", errorMessage, enums.MessageType.DANGER);
            }
        };
        LoginService.addEventListener(listener);
    }

    @Override
    public void initComponents() {
        sharedPreferences = getMyApplication().getSharedPreferenceUtil();
    }

    @OnClick(R.id.btnLogin)
    void onLoginClick(View view){

        if (validate()) {
            String username=txtEmail.getText().toString();
            String password=txtPassword.getText().toString();
            LoginService.doProcessLogin(username, password);




        }

    }

    @OnClick(R.id.btnRememberPassword)
    void onRememberPasswordClick(View view){
        redirect(RememberPasswordActivity.class);
    }

    private boolean validate() {
        boolean success = true;

        if (txtEmail.getText().toString().isEmpty()) {
            txtEmail.setError("Ingrese por favor su usuario.");
            success = false;
        }

        if (txtPassword.getText().toString().isEmpty()) {
            txtPassword.setError("Ingrese por favor su contraseña.");
            success = false;
        }

        return success;
    }
}
