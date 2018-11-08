package ivms.sysware.com.ivmsmex.activities.intro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.BaseActivity;
import ivms.sysware.com.ivmsmex.activities.rastreo.RastreoActivity;

public class VideoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        redirect(RastreoActivity.class);
    }
}
