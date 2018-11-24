package ivms.sysware.com.ivmsmex.activities.rememberPassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ivms.sysware.com.ivmsmex.R;

public class RememberPasswordActivity extends AppCompatActivity {

    @BindView(R.id.customToolbar)
    Toolbar toolbar = null;

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
}
