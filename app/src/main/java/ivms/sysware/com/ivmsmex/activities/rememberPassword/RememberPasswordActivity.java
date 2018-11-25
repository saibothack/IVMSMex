package ivms.sysware.com.ivmsmex.activities.rememberPassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ivms.sysware.com.ivmsmex.R;

public class RememberPasswordActivity extends AppCompatActivity {

    @BindView(R.id.customToolbar)
    Toolbar toolbar;

    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember_password);
        ButterKnife.bind(this);
        showToolbar();
    }

    public void showToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.RememberPasswd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.btnRememberPassword)
    void onRememberPassword(View view){
        if (validate()) {

        }
    }

    private boolean validate() {
        boolean success = true;

        if (txtEmail.getText().toString().isEmpty()) {
            txtEmail.setError("Ingrese por favor su correo.");
            success = false;
        }

        return success;
    }
}
